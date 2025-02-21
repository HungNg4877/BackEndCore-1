package com.example.demo.Controller;

import com.example.demo.Common.SuccessMessage;
import com.example.demo.Dto.PermissionDto;
import com.example.demo.Dto.Request.PagingRequest;
import com.example.demo.Dto.Response.ApiResponse;
import com.example.demo.Service.Filter.IdFilter;
import com.example.demo.Service.Permission.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.demo.Common.EndPointConstant.PERMISSIONS;
import static com.example.demo.Common.EndPointConstant.VIEW_LIST;

@RestController
@RequestMapping(value = PERMISSIONS)
public class PermissionController {
    @Autowired
    private PermissionService permissionService;
    @PostMapping
    public ResponseEntity<ApiResponse<PermissionDto>> createPermission(@RequestBody PermissionDto request) {
        PermissionDto response = permissionService.createPermission(request);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.CREATED.value(), SuccessMessage.SUCCESS.getMessage(),response));
    }
    @DeleteMapping
    public ResponseEntity<ApiResponse<Void>> deletePermission(@RequestBody PermissionDto request){
        permissionService.deletePermission(request);
        return  ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), SuccessMessage.SUCCESS.getMessage(), null));
    }
    @PutMapping
    public ResponseEntity<ApiResponse<Void>> updatePermission(@RequestBody PermissionDto request){
        permissionService.updatePermission(request);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), SuccessMessage.SUCCESS.getMessage(), null));
    }
    @PostMapping(VIEW_LIST)
    public ResponseEntity<ApiResponse<Page<PermissionDto>>> getPermission(@RequestBody PagingRequest<IdFilter> pagingRequest){
        Page<PermissionDto> response = permissionService.getPermission(pagingRequest);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), SuccessMessage.SUCCESS.getMessage(), response));
    }
}
