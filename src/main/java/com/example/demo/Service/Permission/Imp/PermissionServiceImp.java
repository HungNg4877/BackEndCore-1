package com.example.demo.Service.Permission.Imp;

import com.example.demo.Common.ErrorCode;
import com.example.demo.Dto.PermissionDto;
import com.example.demo.Dto.Request.PagingRequest;
import com.example.demo.Dto.RoleDto;
import com.example.demo.Entity.Permission;
import com.example.demo.Entity.Role;
import com.example.demo.Exception.BaseException;
import com.example.demo.Repository.PermissionRepository;
import com.example.demo.Repository.RoleRepository;
import com.example.demo.Service.Filter.IdFilter;
import com.example.demo.Service.Permission.Mapper.PermissionMapper;
import com.example.demo.Service.Permission.PermissionService;
import com.example.demo.Utill.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PermissionServiceImp implements PermissionService {
    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public PermissionDto createPermission(PermissionDto request){
        Permission permission = new Permission();
        permission.setName(request.getName());
        permission.setMethod(request.getMethod());
        permission.setModules(request.getModule());
        permission.setApiPath(request.getApiPath());
        permissionRepository.save(permission);
        return permissionMapper.mapper(permission);
    }
    @Override
    public Void deletePermission(PermissionDto request){
        Permission permission = findById(request.getId());
        permission.setDelete(true);
        permissionRepository.save(permission);
        return null;
    }
    @Override
    public Void updatePermission(PermissionDto request) {
        Permission permission = findById(request.getId());
        permission.setName(request.getName());
        permission.setMethod(request.getMethod());
        permission.setModules(request.getModule());
        permission.setApiPath(request.getApiPath());
        permissionRepository.save(permission);
        return null;
    }
    @Override
    public Page<PermissionDto> getPermission(PagingRequest<IdFilter> pagingRequest){
        Role role = roleRepository.findById(UUID.fromString(pagingRequest.getFilter().getId()))
                .orElseThrow(()-> new BaseException(ErrorCode.FAILED));
        Pageable pageable = PageUtil.getPageRequest(pagingRequest);
        Page<Permission> permissions = permissionRepository.findAllPermssionById(role.getId(),pageable);
        List<PermissionDto> responseList = permissions
                .stream()
                .map(permission -> permissionMapper.mapper(permission))
                .collect(Collectors.toList());
        return new PageImpl<>(responseList,pageable,permissions.getTotalElements());
    }
    public Permission findById(String id){
        return permissionRepository.findById(UUID.fromString(id))
                .orElseThrow(()-> new BaseException(ErrorCode.FAILED));
    }

}
