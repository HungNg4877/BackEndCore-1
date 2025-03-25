package com.example.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String profilePictureUrl;
    private String content;
    private long totalShares;
    private long totalLikes;
    private long totalComments;
    private boolean isLiked;
    private boolean isOwner;
    private PostDTO parentPost;
    private List<MediaDTO> mediaList;
    private LocalDateTime createdAt;
    private LocalDateTime lastModified;
}
