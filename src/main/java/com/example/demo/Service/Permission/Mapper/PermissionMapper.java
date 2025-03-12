package com.example.demo.Service.Permission.Mapper;

import com.example.demo.Dto.PermissionDto;
import com.example.demo.Entity.Permission;
import org.springframework.stereotype.Service;

@Service
public class PermissionMapper {
    public PermissionDto mapper (Permission permission){
        return PermissionDto.builder()
                .id(permission.getId().toString())
                .name(permission.getName())
                .apiPath(permission.getApiPath())
                .method(permission.getMethod())
                .module(permission.getModules())
                .isDelete(permission.isDelete())
                .build();
    }
}
