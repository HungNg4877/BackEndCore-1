package com.example.demo.Common.Error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ValidateFieldMessage {
    public static final String PASSWORD = "password-invalid";
    public static final String EMAIL_ADDRESS = "email-invalid";
}
