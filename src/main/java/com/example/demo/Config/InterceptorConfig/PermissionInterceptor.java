package com.example.demo.Config.InterceptorConfig;

import com.example.demo.Common.Error.ErrorMessage;
import com.example.demo.Entity.Permission;
import com.example.demo.Entity.RolePermission;
import com.example.demo.Entity.User;
import com.example.demo.Exception.BaseException;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.User.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class PermissionInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
//    Denide Request// Request
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
        String httpMethod = request.getMethod();

        // Lấy email từ JWT
        User user = userService.getCurrentUser();
        String email = user.getEmail();
        if (!email.isEmpty()) {
            userRepository.findByEmail(email).orElseThrow(() -> new BaseException(ErrorMessage.USER_DOES_NOT_EXIST));
            if (user != null) {
                List<Permission> permissions = user.getRole().getRolePermissions().stream()
                        .map(RolePermission::getPermission)  // List<RolePermission> to Stream<Permission>
                        .collect(Collectors.toList());
                boolean isAllowed = permissions.stream()
                        .anyMatch(permission -> permission.getApiPath().equals(path) && permission.getMethod().equals(httpMethod));
                if (!isAllowed) {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "no-permission-access-endpoint");
                    return false;
                }
            }
        }
        return true;
    }
}
