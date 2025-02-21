package com.example.demo.Service.Post.Mapper;

import com.example.demo.Dto.MediaDto;
import com.example.demo.Dto.PostDto;
import com.example.demo.Entity.Media;
import com.example.demo.Entity.Post;
import com.example.demo.Entity.User;
import com.example.demo.Repository.CommentRepository;
import com.example.demo.Repository.LikeRepository;
import com.example.demo.Repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostMapper {
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;
    private final PostRepository postRepository;

    public PostDto from(Post post, List<Media> mediaList, PostDto parentPost, User user) {
        List<MediaDto> mediaDtoList = getMediaDtoList(mediaList);
        long totalLikes = likeRepository.getTotalLikes(post.getId());
        long totalComments = commentRepository.getTotalComments(post.getId());
        long totalShares = postRepository.getTotalShares(post.getId());
        boolean isLiked = likeRepository.findByUserAndPost(user.getId(), post.getId()) > 0;
        boolean isOwner = post.getUser().getId().equals(user.getId());
        return PostDto.builder()
                .id(post.getId().toString())
                .content(post.getContent())
                .firstName(post.getUser().getFirstName())
                .lastName(post.getUser().getLastName())
                .profilePictureUrl(post.getUser().getProfilePictureUrl())
                .totalLikes(totalLikes)
                .totalComments(totalComments)
                .isLiked(isLiked)
                .isOwner(isOwner)
                .totalShares(totalShares)
                .parentPost(parentPost)
                .mediaList(mediaDtoList)
                .createdAt(post.getCreatedAt())
                .lastModified(post.getLastModified())
                .build();
    }

    private static List<MediaDto> getMediaDtoList(List<Media> mediaList) {
        List<MediaDto> mediaDtoList = new ArrayList<>();
        for (Media media : mediaList) {
            mediaDtoList.add(new MediaDto(media.getId().toString(), media.getMediaType(), media.getMediaUrl()));
        }
        return mediaDtoList;
    }
}