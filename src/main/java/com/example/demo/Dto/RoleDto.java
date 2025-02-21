package com.example.demo.Dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.core.util.Json;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleDto {
    private String id;
    private String name;
    private String isDelete;
}
