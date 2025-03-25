package com.example.demo.Service.Auth;

import com.example.demo.DTO.Request.LoginRequest;
import com.example.demo.DTO.Request.RegisterRequest;
import com.example.demo.DTO.Request.SetPasswordRequest;
import com.example.demo.DTO.Response.RegisterResponse;
import com.example.demo.DTO.TokenDTO;
import jakarta.servlet.http.HttpServletRequest;


public interface AuthService {
    TokenDTO authenticate(LoginRequest request);
    TokenDTO refreshToken(HttpServletRequest request);
    Void logout(String refreshToken);
    RegisterResponse saveUser(RegisterRequest registerRequest);
    String generateOtp(String email);
    TokenDTO verifyAccount(String email, String otp);
    String setPassword(SetPasswordRequest setPasswordRequest);
}
