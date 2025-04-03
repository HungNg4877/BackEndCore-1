package com.example.demo.Controller;

import com.example.demo.DTO.Response.ApiResponse;
import com.example.demo.Entity.User;
import com.example.demo.Service.User.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.demo.Common.EndPoint.EndPointConstant.*;

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
    @GetMapping
    public ResponseEntity<ApiResponse<User>> getUser() {
        User response = userService.getCurrentUser();
        return ResponseEntity.ok(
                new ApiResponse<>(HttpStatus.OK.value(), "Get Current User", response));
    }

}
