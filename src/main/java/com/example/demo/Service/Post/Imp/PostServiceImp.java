package com.example.demo.Service.Post.Imp;

import com.example.demo.Common.Cloudinary.CloudinaryConstant;
import com.example.demo.Common.Error.ErrorMessage;
import com.example.demo.Common.PostConstant.PostConstant;
import com.example.demo.Common.PostConstant.MediaType;
import com.example.demo.DTO.PostDTO;
import com.example.demo.DTO.Request.PagingRequest;
import com.example.demo.DTO.Request.PostRequest;
import com.example.demo.DTO.Response.UserResponse;
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
    public PostDTO createPost(PostRequest postRequest) {
        User user = userService.getCurrentUser();
        Post parentPost = StringUtils.isBlank(postRequest.getParentPostId())
                ? null
                : postRepository.findPostById(UUID.fromString(postRequest.getParentPostId())).orElseThrow();
        Post post = Post.builder()
                .content(postRequest.getContent())
                .user(user)
                .isDelete(false)
                .parentPost(parentPost)
                .build();
        post = postRepository.saveAndFlush(post);
        if (postRequest.getFiles() != null &&
                postRequest.getFiles().stream().anyMatch(file -> StringUtils.isNotBlank(file))) {
            saveAllMediaFiles(postRequest.getFiles(), post);
        }
        return getPostDto(post.getId().toString());
    }


    @Override
    public PostDTO updatePost(PostRequest postRequest) {
        Post post = getPost(postRequest.getPostId());
        if (post.getUser().getId() != userService.getCurrentUser().getId()) {
            throw new BaseException(ErrorMessage.UNAUTHORIZED);
        }
        post.setContent(postRequest.getContent());
        if (postRequest.getFiles() != null &&
                postRequest.getFiles().stream().anyMatch(file -> StringUtils.isNotBlank(file))) {
            saveAllMediaFiles(postRequest.getFiles(), post);
        }
        List<UUID> deletedFileIds = postRequest.getDeleteFileIds().stream().map(UUID::fromString).toList();

        deleteMedia(deletedFileIds, UUID.fromString(postRequest.getPostId()));
        deleteMediaInCloud(postRequest.getDeleteFileIds(), postRequest.getPostId(), PostConstant.POST);
        post = postRepository.saveAndFlush(post);
        return getPostDto(post.getId().toString());
    }

    @Override
    public void deletePost(String postId) {
        Post post = postRepository.findPostById(UUID.fromString(postId))
                .orElseThrow(() -> new BaseException(ErrorMessage.POST_DOES_NOT_EXIST));
        if (post.getUser().getId() != userService.getCurrentUser().getId()) {
            throw new BaseException(ErrorMessage.UNAUTHORIZED);
        }
        post.setDelete(true);
        postRepository.save(post);
    }

    @Override
    public Page<PostDTO> getNews(PagingRequest pagingRequest) {
        Pageable pageable = PageUtil.getPageRequest(pagingRequest);
        Page<Post> posts = postRepository.getNews(pageable);
        List<PostDTO> postsDto = posts.stream().map(
                post -> getPostDto(post.getId().toString())
        ).toList();
        return new PageImpl<>(postsDto, pageable, posts.getTotalElements());
    }

    @Override
    public PostDTO getPostDto(String postId) {
        User user = userService.getCurrentUser();
        Post post = getPost(postId);
        List<Media> mediaList = getMediaByPostId(post.getId());
        PostDTO parentDto = ObjectUtils.isEmpty(post.getParentPost()) ? null :
                postMapper.from(post.getParentPost(), getMediaByPostId(post.getParentPost().getId()), null, user);
        return postMapper.from(post, mediaList, parentDto, user);
    }

    @Override
    public Post getPost(String postId) {
        return postRepository.findPostById(UUID.fromString(postId))
                .orElseThrow(() -> new BaseException(ErrorMessage.FAILED));
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
                    PostConstant.POST,
                    String.format(CloudinaryConstant.CLOUDINARY_PUBLIC_ID_SAVE_FORMAT, post.getId(), "/", media.getId()));
            media.setMediaUrl(data.get(CloudinaryConstant.CLOUDINARY_URL).toString());
            mediaList.add(mediaRepository.saveAndFlush(media));
        }
        return mediaList;
    }

    private static String getBase64WithoutHeader(String mediaFile) {
        int index;
        index = mediaFile.indexOf("base64,");
        if (index < 0) {
            throw new BaseException(ErrorMessage.FAILED);
        }
        index = index + 7;
        return mediaFile.substring(index);
    }

    private static MediaType getMediaType(String mediaFile) {
        int index = mediaFile.indexOf("data:");
        if (index < 0) {
            throw new BaseException(ErrorMessage.FAILED);
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
                    CloudinaryConstant.CLOUDINARY_PUBLIC_ID_DELETE_FORMAT,
                    folder,
                    prefix != null ? "/" + prefix : "",
                    "/",
                    deleteFile
            );
            cloudinaryService.deleteFile(publicId);
        }
    }

}
