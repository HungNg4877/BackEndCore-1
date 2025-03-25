package com.example.demo.Service.Permission.Mapper;

import com.example.demo.DTO.PermissionDTO;
import com.example.demo.Entity.Permission;
import org.springframework.stereotype.Service;

@Service
public class PermissionMapper {
    public PermissionDTO mapper (Permission permission){
        return PermissionDTO.builder()
                .id(permission.getId().toString())
                .name(permission.getName())
                .apiPath(permission.getApiPath())
                .method(permission.getMethod())
                .module(permission.getModules())
                .isDelete(permission.isDelete())
                .build();
    }
}
