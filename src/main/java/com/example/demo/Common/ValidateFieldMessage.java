package com.example.demo.Common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ValidateFieldMessage {
    public static final String PASSWORD = "password-invalid";
    public static final String EMAIL_ADDRESS = "email-invalid";
}
