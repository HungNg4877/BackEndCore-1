package com.example.demo.Service.Role.Mapper;

import com.example.demo.Dto.RoleDto;
import com.example.demo.Entity.Role;
import org.springframework.stereotype.Service;

@Service
public class RoleMapper {
    public RoleDto mapper (Role role){
        return RoleDto.builder()
                .id(role.getId().toString())
                .name(role.getName())
                .isDelete(role.isDelete())
                .build();
    }
}
