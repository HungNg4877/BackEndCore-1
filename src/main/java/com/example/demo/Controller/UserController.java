package com.example.demo.Controller;

import com.example.demo.Dto.Response.ApiResponse;
import com.example.demo.Service.User.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.demo.Common.EndPointConstant.*;

@RestController
@RequestMapping(value = USERS)
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @DeleteMapping(value = USER_ID)
    public ResponseEntity<ApiResponse<String>> deleteUser(@PathVariable("userId") String id) {
        userService.softDeleteUser(id);
        return ResponseEntity.ok(
                new ApiResponse<>(HttpStatus.OK.value(), "User deleted successfully", null));
    }
}
