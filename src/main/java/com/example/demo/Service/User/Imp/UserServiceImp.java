package com.example.demo.Service.User.Imp;

import com.example.demo.Common.Error.ErrorCode;
import com.example.demo.Entity.User;
import com.example.demo.Exception.BaseException;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.User.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;
    // Fix Get current login
    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // JWTAuthenticationToken: Token + Principal(Info User) + Authorities
        if (!(authentication instanceof JwtAuthenticationToken)) {
            throw new BaseException(ErrorCode.FAILED);
        }
        JwtAuthenticationToken jwtAuth = (JwtAuthenticationToken) authentication; // Get Info Authority
        String email = jwtAuth.getToken().getClaim("sub"); // sub => email
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new BaseException(ErrorCode.USER_DOES_NOT_EXIST));
    }

    @Override
    public void softDeleteUser(String userId) {
        User user = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new BaseException(ErrorCode.USER_DOES_NOT_EXIST));
        user.setDelete(true);
        userRepository.save(user);
    }
}

