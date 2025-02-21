package com.example.demo.Dto;

import com.example.demo.Common.MediaType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MediaDto {
    private String id;
    private MediaType type;
    private String url;

}
