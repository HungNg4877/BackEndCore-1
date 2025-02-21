package com.example.demo.Service.Permission;

import com.example.demo.Dto.PermissionDto;
import com.example.demo.Dto.Request.PagingRequest;
import com.example.demo.Service.Filter.IdFilter;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.List;

public interface PermissionService {
    PermissionDto createPermission(PermissionDto request);
    Void deletePermission(PermissionDto request);
    Void updatePermission(PermissionDto request);
    Page<PermissionDto> getPermission(PagingRequest<IdFilter> pagingRequest);
}
