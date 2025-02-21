package com.example.demo.Common;

import lombok.Getter;

@Getter
public enum SuccessMessage {
    LOGIN_SUCCESS("login-successfully"),
    SUCCESS("successfully"),
    REGISTER_SUCCESS("register-successfully");
    private final String message;
    SuccessMessage(String s) {
        this.message = s;
    }
}
