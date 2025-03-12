package com.example.demo.Utill;

import com.example.demo.Dto.Request.LoginRequest;
import com.example.demo.Entity.Permission;
import com.example.demo.Entity.User;
import com.nimbusds.jose.util.Base64;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SecurityUtil {
    @Autowired
    private JwtEncoder jwtEncoder;

    public static final MacAlgorithm JWT_ALGORITHM = MacAlgorithm.HS256;

    @Value("${application.security.jwt.secret-key}")
    private String jwtKey;

    @Value("${application.security.jwt.expiration}")
    private long accessTokenExpiration;

    @Value("${application.security.jwt.refresh-token.expiration}")
    private long refreshTokenExpiration;



    public String createAccessToken(User request) {

        Instant now = Instant.now();
        Instant validity = now.plus(this.accessTokenExpiration, ChronoUnit.SECONDS);
        List<String> permission = request.getRole().getRolePermissions().stream()
                .map(rolePermission -> rolePermission.getPermission())// Lấy ra các đối tượng Permission
                .map(Permission::getApiPath)// Lấy giá trị apiPath từ mỗi Permission
                .collect(Collectors.toList()); // Thu thập kết quả thành một List<String> chứa tất cả apiPath.

        // @formatter:off //Data in Token (Payload)
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuedAt(now)
                .expiresAt(validity)
                .subject(request.getEmail())
                .claim("user", request.getEmail())
                .claim("permission", permission)
                .build();

        JwsHeader jwsHeader = JwsHeader.with(JWT_ALGORITHM).build();
        // Key + Header (Algorithm) + Payload (Claims)
        return this.jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).getTokenValue();

    }

    public String createRefreshToken(User request) {
        Instant now = Instant.now();
        Instant validity = now.plus(this.refreshTokenExpiration, ChronoUnit.SECONDS);
        List<String> permission = request.getRole().getRolePermissions().stream()
                .map(rolePermission -> rolePermission.getPermission())
                .map(Permission::getApiPath)
                .collect(Collectors.toList());
        // @formatter:off
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuedAt(now)
                .expiresAt(validity)
                .subject(request.getEmail())
                .claim("user", request.getEmail())
                .claim("permission", permission)
                .build();

        JwsHeader jwsHeader = JwsHeader.with(JWT_ALGORITHM).build();
        return this.jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).getTokenValue();

    }


//1. Client -> Token in Header Request
//2. After Config OAUTH2 -> Auto Active filter BearerTokenAuthenticationFilter -> Auto Substring BearToken
//-> Decocde -> Find Singature and Comapare
}