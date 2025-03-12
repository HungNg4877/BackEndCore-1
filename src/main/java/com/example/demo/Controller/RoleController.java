package com.example.demo.Controller;

import com.example.demo.Common.Success.SuccessMessage;
import com.example.demo.Dto.Response.ApiResponse;
import com.example.demo.Dto.RoleDto;
import com.example.demo.Service.Role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demo.Common.EndPoint.EndPointConstant.ROLES;

@RestController
@RequestMapping(value = ROLES)
public class RoleController {
    @Autowired
    private RoleService roleService;
    @PostMapping
    public ResponseEntity<ApiResponse<RoleDto>> createRole(@RequestBody RoleDto request) {
        RoleDto response = roleService.createRole(request);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.CREATED.value(), SuccessMessage.SUCCESS.getMessage(),response));
    }
    @DeleteMapping
    public ResponseEntity<ApiResponse<Void>> deleteRole(@RequestBody RoleDto request){
        roleService.deleteRole(request);
        return  ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), SuccessMessage.SUCCESS.getMessage(), null));
    }
    @PutMapping
    public ResponseEntity<ApiResponse<RoleDto>> updateRole(@RequestBody RoleDto request){
        RoleDto response = roleService.updateRole(request);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), SuccessMessage.SUCCESS.getMessage(), response));
    }
    @GetMapping
    public ResponseEntity<ApiResponse<List<RoleDto>>> getRole(){
        List<RoleDto> response = roleService.getRole();
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), SuccessMessage.SUCCESS.getMessage(), response));
    }
}
