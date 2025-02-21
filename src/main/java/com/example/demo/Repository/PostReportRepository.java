package com.example.demo.Repository;

import com.example.demo.Entity.PostReport;
import com.example.demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface PostReportRepository extends JpaRepository<PostReport, UUID> {
    Optional<PostReport> findByUser(User user);

    @Query("SELECT COUNT(r) FROM PostReport r WHERE r.post.id = :postId ")
    int findTotalReport(@Param("postId") UUID postId);
}
