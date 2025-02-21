package com.example.demo.Dto.Request;

import com.example.demo.Common.PatternConstant;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class RegisterRequest {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @Pattern(
            regexp = PatternConstant.EMAIL_PATTERN,
            message = "email-not-correct-format"
    )
    private String email;
    @Pattern(
            regexp = PatternConstant.PHONE_NUMBER_PATTERN ,
            message = "invalid-phone-number."
    )
    private String phoneNumber;
    @Pattern(
            regexp = PatternConstant.PASSWORD_PATTERN ,
            message = "password-invalid"
    )
    private String password;

    private String confirmPassword;

}
