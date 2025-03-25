package com.example.demo.Service.Permission;

import com.example.demo.DTO.PermissionDTO;
import com.example.demo.DTO.Request.PagingRequest;
import com.example.demo.Service.Filter.IdFilter;
import org.springframework.data.domain.Page;

public interface PermissionService {
    PermissionDTO createPermission(PermissionDTO request);
    Void deletePermission(PermissionDTO request);
    PermissionDTO updatePermission(PermissionDTO request);
    Page<PermissionDTO> getPermission(PagingRequest<IdFilter> pagingRequest);
}
