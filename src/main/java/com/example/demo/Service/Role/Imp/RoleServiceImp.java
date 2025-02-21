package com.example.demo.Service.Role.Imp;

import com.example.demo.Common.ErrorCode;
import com.example.demo.Dto.RoleDto;
import com.example.demo.Entity.Role;
import com.example.demo.Exception.BaseException;
import com.example.demo.Repository.RoleRepository;
import com.example.demo.Service.Role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RoleServiceImp implements RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public RoleDto createRole(RoleDto request){
        Role role = new Role();
        role.setName(request.getName());
        roleRepository.save(role);
        return RoleDto.builder()
                .id(role.getId().toString())
                .name(role.getName())
                .build();
    }
    @Override
    public Void deleteRole(RoleDto request){
        Role role = new Role();
        roleRepository.findByName(request.getName());
        role.setDelete(true);
        roleRepository.save(role);
        return null;
    }
    @Override
    public RoleDto updateRole(RoleDto request){
        Role role = roleRepository.findById(UUID.fromString(request.getId()))
                .orElseThrow(() -> new BaseException(ErrorCode.FAILED));
        role.setName(request.getName());
        roleRepository.save(role);
        return RoleDto.builder()
                .id(role.getId().toString())
                .name(role.getName())
                .build();
    }
    @Override
    public List<RoleDto> getRole(){
        List<Role> roles = roleRepository.findAll();
        return roles.stream()
                .map(role -> RoleDto.builder()
                        .id(role.getId().toString())
                        .name(role.getName())
                        .build())
                .collect(Collectors.toList());
    }

}
