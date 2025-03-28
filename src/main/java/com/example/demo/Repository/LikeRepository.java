package com.example.demo.Repository;

import com.example.demo.Entity.Like;
import com.example.demo.Entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface LikeRepository extends JpaRepository<Like,UUID> {
    @Query("SELECT count(l.id) FROM Like l WHERE  l.post.id = :postId")
    long getTotalLikes(@Param("postId") UUID postId);
    @Query("SELECT count(l.id) FROM Like l WHERE l.user.id = :userId and l.post.id = :postId")
    int findByUserAndPost(@Param("userId") UUID userId,@Param("postId") UUID postId);
}
