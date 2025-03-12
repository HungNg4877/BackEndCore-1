package com.example.demo.Service.Role.Imp;

import com.example.demo.Common.Error.ErrorCode;
import com.example.demo.Dto.RoleDto;
import com.example.demo.Entity.Role;
import com.example.demo.Exception.BaseException;
import com.example.demo.Repository.RoleRepository;
import com.example.demo.Service.Role.Mapper.RoleMapper;
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
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public RoleDto createRole(RoleDto request){
        Role role = new Role();
        role.setName(request.getName());
        role.setDelete(request.isDelete());
        roleRepository.save(role);
        return roleMapper.mapper(role);
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
        return roleMapper.mapper(role);
    }
    @Override
    public List<RoleDto> getRole(){
        List<Role> roles = roleRepository.findAll();
        return roles.stream()
                .map(role -> roleMapper.mapper(role))
                .collect(Collectors.toList());
    }

}
