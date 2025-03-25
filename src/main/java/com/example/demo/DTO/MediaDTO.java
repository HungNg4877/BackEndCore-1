package com.example.demo.DTO;

import com.example.demo.Common.PostConstant.MediaType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MediaDTO {
    private String id;
    private MediaType type;
    private String url;

}
