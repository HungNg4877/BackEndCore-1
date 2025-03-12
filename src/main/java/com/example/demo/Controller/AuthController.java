package com.example.demo.Controller;

import com.example.demo.Common.Success.SuccessMessage;
import com.example.demo.Dto.Request.LoginRequest;
import com.example.demo.Dto.Request.RegisterRequest;
import com.example.demo.Dto.Request.SetPasswordRequest;
import com.example.demo.Dto.Response.ApiResponse;
import com.example.demo.Dto.Response.RegisterResponse;
import com.example.demo.Dto.TokenDto;
import com.example.demo.Service.Auth.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.demo.Common.EndPoint.EndPointConstant.*;

@RestController
@RequestMapping(value = AUTH)
public class AuthController {

    @Autowired
    private AuthService authService;
    @PostMapping(value = AUTH_LOGIN)
    public ResponseEntity<ApiResponse<TokenDto>> login(@RequestBody @Valid LoginRequest request) {
        TokenDto tokenReponse = authService.authenticate(request);
        return ResponseEntity.ok()
                .body(new ApiResponse<>(HttpStatus.OK.value(), SuccessMessage.LOGIN_SUCCESS.getMessage(), tokenReponse));
    }
    @PostMapping(value = REFRESH_TOKEN)
    public ResponseEntity<ApiResponse<TokenDto>> refreshToken(HttpServletRequest request) {
        TokenDto tokenReponse = authService.refreshToken(request);
        return ResponseEntity.ok()
                .body(new ApiResponse<>(HttpStatus.OK.value(), SuccessMessage.LOGIN_SUCCESS.getMessage(), tokenReponse));
    }
    @PostMapping(value = AUTH_REGISTER)
    public ResponseEntity<ApiResponse<RegisterResponse>> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        RegisterResponse RegisterResponse = this.authService.saveUser(registerRequest);
        return ResponseEntity.ok(
                new ApiResponse<>(HttpStatus.CREATED.value(), SuccessMessage.REGISTER_SUCCESS.getMessage(), RegisterResponse));
    }
    @PostMapping(value = AUTH_LOGOUT)
    public ResponseEntity<ApiResponse<Void>> logout(String refreshToken) {
        authService.logout(refreshToken);
        return ResponseEntity.ok()
                .body(new ApiResponse<>(HttpStatus.OK.value(), SuccessMessage.REGISTER_SUCCESS.getMessage(), null));
    }
    @PutMapping(value = AUTH_VERIFY_OTP)
    public ResponseEntity<ApiResponse<TokenDto>> verifyAccount(@RequestParam(name = "email") String email,
                                                               @RequestParam(name = "otp") String otp) {
        TokenDto verifyOtpResponse = authService.verifyAccount(email,otp);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), SuccessMessage.SUCCESS.getMessage(), verifyOtpResponse));
    }
    @PostMapping(value = AUTH_FORGOT_PASSWORD)
    public ResponseEntity<ApiResponse<String>> forgotPassword(@PathVariable String email) {
        String otp = authService.generateOtp(email);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), otp, null));
    }
    @PutMapping(value = AUTH_SET_PASSWORD)
    public ResponseEntity<ApiResponse<String>> setPassword(@Valid @RequestBody SetPasswordRequest setPasswordRequest) {
        String response = authService.setPassword(setPasswordRequest);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), response, null));
    }
}
