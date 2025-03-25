package com.example.demo.Service.Role.Mapper;

import com.example.demo.DTO.RoleDTO;
import com.example.demo.Entity.Role;
import org.springframework.stereotype.Service;

@Service
public class RoleMapper {
    public RoleDTO mapper (Role role){
        return RoleDTO.builder()
                .id(role.getId().toString())
                .name(role.getName())
                .isDelete(role.isDelete())
                .build();
    }
}
