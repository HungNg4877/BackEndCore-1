package com.example.demo.Service.Comment.Mapper;

import com.example.demo.Dto.CommentDto;
import com.example.demo.Entity.Comment;
import com.example.demo.Entity.User;
import com.example.demo.Repository.CommentRepository;
import com.example.demo.Repository.LikeCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentMapper {
    @Autowired
    private LikeCommentRepository likeCommentRepository;
    private CommentRepository commentRepository;

    public CommentDto toDTO(Comment comment, User user) {

        CommentDto commentDTO = new CommentDto();
        commentDTO.setCommentId(comment.getId().toString());
        commentDTO.setPostId(comment.getPost().getId().toString());
        commentDTO.setUserId(comment.getUser().getId().toString());
        if (comment.getParentComment() != null) {
            commentDTO.setParentCommentId(comment.getParentComment().getId().toString());
        } else {
            commentDTO.setParentCommentId(null);
        }
        commentDTO.setHidden(comment.isHidden());
        commentDTO.setDelete(comment.isDelete());
        commentDTO.setCommentText(comment.getCommentText());
        commentDTO.setCreatedAt(comment.getCreatedAt());
        commentDTO.setLastModified(comment.getLastModified());
        commentDTO.setLiked(likeCommentRepository.findByUserAndComment(user, comment).isPresent());
        commentDTO.setOwnedPost(comment.getPost().getUser().getId().equals(user.getId()));
        commentDTO.setOwnedComment(comment.getUser().getId().equals(user.getId()));
        commentDTO.setFirstName(comment.getUser().getFirstName());
        commentDTO.setLastName(comment.getUser().getLastName());
        commentDTO.setProfilePictureUrl(comment.getUser().getProfilePictureUrl());
        commentDTO.setTotalLikes(likeCommentRepository.countDistinctLikesByCommentId(comment.getId()));

        List<CommentDto> childComments = (comment.getChildComments() == null)
                ? new ArrayList<>()
                : comment.getChildComments().stream()
                .filter(childComment -> childComment instanceof Comment)
                .filter(childComment -> !childComment.isHidden() && !childComment.isDelete())
                .map(childComment -> toDTO(childComment, user))
                .collect(Collectors.toList());

        commentDTO.setChildComments(childComments);

        return commentDTO;
    }
}
