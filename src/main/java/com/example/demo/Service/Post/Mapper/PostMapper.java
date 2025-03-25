package com.example.demo.Service.Post.Mapper;

import com.example.demo.DTO.MediaDTO;
import com.example.demo.DTO.PostDTO;
import com.example.demo.Entity.Media;
import com.example.demo.Entity.Post;
import com.example.demo.Entity.User;
import com.example.demo.Repository.CommentRepository;
import com.example.demo.Repository.LikeRepository;
import com.example.demo.Repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostMapper {
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;
    private final PostRepository postRepository;

    public PostDTO from(Post post, List<Media> mediaList, PostDTO parentPost, User user) {
        List<MediaDTO> mediaDTOList = mediaList.stream()
                .map(media -> new MediaDTO(media.getId().toString(), media.getMediaType(), media.getMediaUrl()))
                .collect(Collectors.toList());

        return PostDTO.builder()
                .id(post.getId().toString())
                .content(post.getContent())
                .firstName(post.getUser().getFirstName())
                .lastName(post.getUser().getLastName())
                .profilePictureUrl(post.getUser().getProfilePictureUrl())
                .totalLikes(likeRepository.getTotalLikes(post.getId()))
                .totalComments(commentRepository.getTotalComments(post.getId()))
                .totalShares(postRepository.getTotalShares(post.getId()))
                .isLiked(likeRepository.findByUserAndPost(user.getId(), post.getId()) > 0)
                .isOwner(post.getUser().getId().equals(user.getId()))
                .parentPost(parentPost)
                .mediaList(mediaDTOList)
                .createdAt(post.getCreatedAt())
                .lastModified(post.getLastModified())
                .build();
    }
}