package com.example.demo.Service.Role;

import com.example.demo.Dto.RoleDto;

import java.util.List;

public interface RoleService {
    RoleDto createRole(RoleDto request);
    Void deleteRole(RoleDto request);
    RoleDto updateRole(RoleDto request);
    List<RoleDto> getRole();
}
