package com.example.demo.Config;

import com.example.demo.Dto.Response.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class CustomAuthEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper mapper;
    public CustomAuthEntryPoint(ObjectMapper mapper) {
        this.mapper = mapper;
    }
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json;charset=UTF-8");
        mapper.writeValue(response.getWriter(), Map.of(
                "code", HttpStatus.UNAUTHORIZED.value(),
                "message", "Unauthorized"
        ));
    }
}