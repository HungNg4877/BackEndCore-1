package com.example.demo.Dto.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePostRequest {
    private String postId;
    private List<String> files;
    private String content;
    private List<String> deleteFileIds;

}
