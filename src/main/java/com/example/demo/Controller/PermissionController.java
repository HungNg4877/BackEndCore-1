package com.example.demo.Controller;

import com.example.demo.Common.Success.SuccessMessage;
import com.example.demo.DTO.PermissionDTO;
import com.example.demo.DTO.Request.PagingRequest;
import com.example.demo.DTO.Response.ApiResponse;
import com.example.demo.Service.Filter.IdFilter;
import com.example.demo.Service.Permission.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.demo.Common.EndPoint.EndPointConstant.PERMISSIONS;
import static com.example.demo.Common.EndPoint.EndPointConstant.VIEW_LIST;

@RestController
@RequestMapping(value = PERMISSIONS)
public class PermissionController {
    @Autowired
    private PermissionService permissionService;
    @PostMapping
    public ResponseEntity<ApiResponse<PermissionDTO>> createPermission(@RequestBody PermissionDTO request) {
        PermissionDTO response = permissionService.createPermission(request);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.CREATED.value(), SuccessMessage.SUCCESS.getMessage(),response));
    }
    @DeleteMapping
    public ResponseEntity<ApiResponse<Void>> deletePermission(@RequestBody PermissionDTO request){
        permissionService.deletePermission(request);
        return  ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), SuccessMessage.SUCCESS.getMessage(), null));
    }
    @PutMapping
    public ResponseEntity<ApiResponse<PermissionDTO>> updatePermission(@RequestBody PermissionDTO request){
        PermissionDTO response = permissionService.updatePermission(request);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), SuccessMessage.SUCCESS.getMessage(), response));
    }
    @PostMapping(VIEW_LIST)
    public ResponseEntity<ApiResponse<Page<PermissionDTO>>> getPermission(@RequestBody PagingRequest<IdFilter> pagingRequest){
        Page<PermissionDTO> response = permissionService.getPermission(pagingRequest);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), SuccessMessage.SUCCESS.getMessage(), response));
    }
}
