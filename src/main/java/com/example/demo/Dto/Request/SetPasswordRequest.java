package com.example.demo.Dto.Request;

import com.example.demo.Common.Pattern.PatternConstant;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SetPasswordRequest {
    @Pattern(
            regexp = PatternConstant.EMAIL_PATTERN ,
            message = "email-not-correct-format"
    )
    private String email;
    @Pattern(
            regexp = PatternConstant.PASSWORD_PATTERN ,
            message = "password-invalid"
    )
    private String newPassword;

    private String confirmNewPassword;
}
