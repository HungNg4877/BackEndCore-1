package com.example.demo.DTO.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostRequest {
    private String postId;
    private List<String> files;
    private String content;
    private List<String> deleteFileIds;
    private String parentPostId;
}
