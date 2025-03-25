package com.example.demo.DTO.Request;

import com.example.demo.Common.Validate.ValidateConstant;
import com.example.demo.Common.Validate.ValidateMessage;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    @Email(regexp = ValidateConstant.EMAIL_ADDRESS,
            message = ValidateMessage.EMAIL_ADDRESS)
    private String email;

    @Pattern(
            regexp = ValidateConstant.PASSWORD,
            message = ValidateMessage.PASSWORD
    )
    private String password;
}
