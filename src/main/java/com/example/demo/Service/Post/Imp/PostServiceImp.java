package com.example.demo.Service.Post.Imp;

import com.example.demo.Common.*;
import com.example.demo.Common.Cloudinary.AttributeConstant;
import com.example.demo.Common.Cloudinary.FormatConstant;
import com.example.demo.Common.Error.ErrorCode;
import com.example.demo.Dto.PostDto;
import com.example.demo.Dto.Request.CreatePostRequest;
import com.example.demo.Dto.Request.PagingRequest;
import com.example.demo.Dto.Request.UpdatePostRequest;
import com.example.demo.Dto.Response.UserResponse;
import com.example.demo.Entity.Media;
import com.example.demo.Entity.Post;
import com.example.demo.Entity.User;
import com.example.demo.Exception.BaseException;
import com.example.demo.Repository.MediaRepository;
import com.example.demo.Repository.PostRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.Cloudinary.CloudinaryService;
import com.example.demo.Service.Filter.IdFilter;
import com.example.demo.Service.Post.Mapper.PostMapper;
import com.example.demo.Service.Post.PostService;
import com.example.demo.Service.User.UserService;
import com.example.demo.Utill.PageUtil;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.*;

@Data
@RequiredArgsConstructor
@Service
public class PostServiceImp implements PostService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CloudinaryService cloudinaryService;
    private final MediaRepository mediaRepository;
    private final UserService userService;
    private final PostMapper postMapper;



    @Override
    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public PostDto createPost(CreatePostRequest createPostRequest) {
        User user = userService.getCurrentUser();
        Post parentPost = StringUtils.isBlank(createPostRequest.getParentPostId())
                ? null
                : postRepository.findPostById(UUID.fromString(createPostRequest.getParentPostId())).orElseThrow();
        Post post = Post.builder()
                .content(createPostRequest.getContent())
                .user(user)
                .isDelete(false)
                .parentPost(parentPost)
                .build();
        post = postRepository.saveAndFlush(post);
        if (createPostRequest.getFiles() != null &&
                createPostRequest.getFiles().stream().anyMatch(file -> StringUtils.isNotBlank(file))) {
            saveAllMediaFiles(createPostRequest.getFiles(), post);
        }
        return getPostDto(post.getId().toString());
    }


    @Override
    public PostDto updatePost(UpdatePostRequest updatePostRequest) {
        Post post = getPost(updatePostRequest.getPostId());
        if (post.getUser().getId() != userService.getCurrentUser().getId()) {
            throw new BaseException(ErrorCode.UNAUTHORIZED);
        }
        post.setContent(updatePostRequest.getContent());
        if (updatePostRequest.getFiles() != null &&
                updatePostRequest.getFiles().stream().anyMatch(file -> StringUtils.isNotBlank(file))) {
            saveAllMediaFiles(updatePostRequest.getFiles(), post);
        }
        List<UUID> deletedFileIds = updatePostRequest.getDeleteFileIds().stream().map(UUID::fromString).toList();

        deleteMedia(deletedFileIds, UUID.fromString(updatePostRequest.getPostId()));
        deleteMediaInCloud(updatePostRequest.getDeleteFileIds(), updatePostRequest.getPostId(), FolderNameConstant.POST);
        post = postRepository.saveAndFlush(post);
        return getPostDto(post.getId().toString());
    }

    @Override
    public void deletePost(String postId) {
        Post post = postRepository.findPostById(UUID.fromString(postId))
                .orElseThrow(() -> new BaseException(ErrorCode.POST_DOES_NOT_EXIST));
        if (post.getUser().getId() != userService.getCurrentUser().getId()) {
            throw new BaseException(ErrorCode.UNAUTHORIZED);
        }
        post.setDelete(true);
        postRepository.save(post);
    }

    @Override
    public Page<PostDto> getNews(PagingRequest pagingRequest) {
        Pageable pageable = PageUtil.getPageRequest(pagingRequest);
        Page<Post> posts = postRepository.getNews(pageable);
        List<PostDto> postsDto = posts.stream().map(
                post -> getPostDto(post.getId().toString())
        ).toList();
        return new PageImpl<>(postsDto, pageable, posts.getTotalElements());
    }

    @Override
    public PostDto getPostDto(String postId) {
        User user = userService.getCurrentUser();
        Post post = getPost(postId);
        List<Media> mediaList = getMediaByPostId(post.getId());
        PostDto parentDto = ObjectUtils.isEmpty(post.getParentPost()) ? null :
                postMapper.from(post.getParentPost(), getMediaByPostId(post.getParentPost().getId()), null, user);
        return postMapper.from(post, mediaList, parentDto, user);
    }

    @Override
    public Post getPost(String postId) {
        return postRepository.findPostById(UUID.fromString(postId))
                .orElseThrow(() -> new BaseException(ErrorCode.FAILED));
    }

    @Override
    public Page<UserResponse> getSharedList(PagingRequest<IdFilter> pagingRequest) {
        Pageable pageable = PageUtil.getPageRequest(pagingRequest);
        Page<User> pageUser = postRepository.getUsersSharedByPost(pageable, UUID.fromString(pagingRequest.getFilter().getId()));
        List<UserResponse> userResponses = pageUser.stream().map(user -> new UserResponse(user.getId().toString(), user.getFirstName(), user.getLastName(), user.getProfilePictureUrl()))
                .toList();
        return new PageImpl<>(userResponses, pageable, pageUser.getTotalElements());
    }

    private List<Media> saveAllMediaFiles(List<String> mediaFiles, Post post) {
        List<Media> mediaList = new ArrayList<>();
        for (String mediaFile : mediaFiles) {
            MediaType mediaType = getMediaType(mediaFile);

            Media media = Media.builder()
                    .isDelete(false)
                    .mediaType(mediaType)
                    .post(post)
                    .build();
            media = mediaRepository.saveAndFlush(media);
            String fileWithoutHeader = getBase64WithoutHeader(mediaFile);
            byte[] decodedBytes = Base64.getDecoder().decode(fileWithoutHeader);
            Map data = cloudinaryService.upload(
                    decodedBytes, mediaType,
                    FolderNameConstant.POST,
                    String.format(FormatConstant.CLOUDINARY_PUBLIC_ID_SAVE_FORMAT, post.getId(), "/", media.getId()));
            media.setMediaUrl(data.get(AttributeConstant.CLOUDINARY_URL).toString());
            mediaList.add(mediaRepository.saveAndFlush(media));
        }
        return mediaList;
    }

    private static String getBase64WithoutHeader(String mediaFile) {
        int index;
        index = mediaFile.indexOf("base64,");
        if (index < 0) {
            throw new BaseException(ErrorCode.FAILED);
        }
        index = index + 7;
        return mediaFile.substring(index);
    }

    private static MediaType getMediaType(String mediaFile) {
        int index = mediaFile.indexOf("data:");
        if (index < 0) {
            throw new BaseException(ErrorCode.FAILED);
        }
        index = index + 5;
        return MediaType.valueOf(mediaFile.substring(index, index + 5).toUpperCase());
    }

    private List<Media> getMediaByPostId(UUID postId) {
        return mediaRepository.findAllByPostId(postId);
    }

    private void deleteMedia(List<UUID> deleteFiles, UUID postId) {
        List<Media> mediaList = mediaRepository.findAllByIdsAndPostId(deleteFiles, postId);
        mediaRepository.deleteAll(mediaList);
    }
//        deleteMediaInCloud(updatePostRequest.getDeleteFileIds(), updatePostRequest.getPostId(), FolderNameConstant.POST);
    private void deleteMediaInCloud(List<String> deleteFiles, String prefix, String folder) {
        for (String deleteFile : deleteFiles) {
            String publicId = String.format(
                    FormatConstant.CLOUDINARY_PUBLIC_ID_DELETE_FORMAT,
                    folder,
                    prefix != null ? "/" + prefix : "",
                    "/",
                    deleteFile
            );
            cloudinaryService.deleteFile(publicId);
        }
    }

}
