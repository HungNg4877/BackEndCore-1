package com.example.demo.Service.Role;

import com.example.demo.DTO.RoleDTO;

import java.util.List;

public interface RoleService {
    RoleDTO createRole(RoleDTO request);
    Void deleteRole(RoleDTO request);
    RoleDTO updateRole(RoleDTO request);
    List<RoleDTO> getRole();
}
