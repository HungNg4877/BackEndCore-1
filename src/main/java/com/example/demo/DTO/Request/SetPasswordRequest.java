package com.example.demo.DTO.Request;

import com.example.demo.Common.Validate.ValidateConstant;
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
            regexp = ValidateConstant.EMAIL_PATTERN ,
            message = "email-not-correct-format"
    )
    private String email;
    @Pattern(
            regexp = ValidateConstant.PASSWORD_PATTERN ,
            message = "password-invalid"
    )
    private String newPassword;

    private String confirmNewPassword;
}
