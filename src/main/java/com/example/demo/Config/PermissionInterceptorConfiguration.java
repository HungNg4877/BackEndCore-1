package com.example.demo.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class PermissionInterceptorConfiguration implements WebMvcConfigurer {

    @Bean
    public PermissionInterceptor permissionInterceptor() {
        return new PermissionInterceptor();
    }
// No Interceptor API
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] whiteList = {"/api/v1/auth/**", "/public/**","/v3/api-docs/**",
                "/swagger-ui/**",
                "/swagger-resources/**",
                "/swagger-ui.html",
                "/api/v1/role/**",
                "/api/v1/permission/**",
                "/api/v1/post/**"
                };
        registry.addInterceptor(permissionInterceptor())
                .excludePathPatterns(whiteList);
    }
}
