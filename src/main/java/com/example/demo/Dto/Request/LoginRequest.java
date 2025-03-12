package com.example.demo.Dto.Request;

import com.example.demo.Common.Pattern.PatternConstant;
import com.example.demo.Common.Error.ValidateFieldMessage;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    @Email(regexp = PatternConstant.EMAIL_ADDRESS,
            message = ValidateFieldMessage.EMAIL_ADDRESS)
    private String email;

    @Pattern(
            regexp = PatternConstant.PASSWORD,
            message = ValidateFieldMessage.PASSWORD
    )
    private String password;
}
