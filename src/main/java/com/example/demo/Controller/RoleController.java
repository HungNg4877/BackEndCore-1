package com.example.demo.Controller;

import com.example.demo.Common.Success.SuccessMessage;
import com.example.demo.DTO.Response.ApiResponse;
import com.example.demo.DTO.RoleDTO;
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
    public ResponseEntity<ApiResponse<RoleDTO>> createRole(@RequestBody RoleDTO request) {
        RoleDTO response = roleService.createRole(request);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.CREATED.value(), SuccessMessage.SUCCESS.getMessage(),response));
    }
    @DeleteMapping
    public ResponseEntity<ApiResponse<Void>> deleteRole(@RequestBody RoleDTO request){
        roleService.deleteRole(request);
        return  ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), SuccessMessage.SUCCESS.getMessage(), null));
    }
    @PutMapping
    public ResponseEntity<ApiResponse<RoleDTO>> updateRole(@RequestBody RoleDTO request){
        RoleDTO response = roleService.updateRole(request);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), SuccessMessage.SUCCESS.getMessage(), response));
    }
    @GetMapping
    public ResponseEntity<ApiResponse<List<RoleDTO>>> getRole(){
        List<RoleDTO> response = roleService.getRole();
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), SuccessMessage.SUCCESS.getMessage(), response));
    }
}
