package com.example.demo.Service.Auth;

import com.example.demo.Dto.Request.LoginRequest;
import com.example.demo.Dto.Request.RegisterRequest;
import com.example.demo.Dto.Request.SetPasswordRequest;
import com.example.demo.Dto.Response.RegisterResponse;
import com.example.demo.Dto.TokenDto;
import jakarta.servlet.http.HttpServletRequest;


public interface AuthService {
    TokenDto authenticate(LoginRequest request);
    TokenDto refreshToken(HttpServletRequest request);
    Void logout(String refreshToken);
    RegisterResponse saveUser(RegisterRequest registerRequest);
    String generateOtp(String email);
    TokenDto verifyAccount(String email, String otp);
    String setPassword(SetPasswordRequest setPasswordRequest);
}
