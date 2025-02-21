package com.example.demo.Repository;

import com.example.demo.Entity.Comment;
import com.example.demo.Entity.LikeComment;
import com.example.demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface LikeCommentRepository extends JpaRepository<LikeComment,UUID> {
    Optional<LikeComment> findByUserAndComment(User user, Comment comment);
    @Query("SELECT COUNT(DISTINCT l) FROM LikeComment l WHERE l.comment.id = :commentId")
    long countDistinctLikesByCommentId(@Param("commentId") UUID commentId);
    Optional<LikeComment> findByCommentId(UUID commentId);
}
