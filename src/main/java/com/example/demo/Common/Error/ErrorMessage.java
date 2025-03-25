package com.example.demo.Common.Error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorMessage {
    USER_DOES_NOT_EXIST(HttpStatus.BAD_REQUEST.value(), "user-does-not-exist"),
    POST_DOES_NOT_EXIST(HttpStatus.BAD_REQUEST.value(), "post-does-not-exist"),
    OTP_DOES_NOT_EXIST(HttpStatus.BAD_REQUEST.value(), "otp-does-not-exist"),
    OTP_EXPIRED(HttpStatus.BAD_REQUEST.value(), "otp-expired"),
    USER_EXIST(HttpStatus.BAD_REQUEST.value(), "user-already-exist"),
    WRONG_PASSWORD(HttpStatus.BAD_REQUEST.value(), "wrong-password"),
    CONFIRM_PASSWORD_DOES_NOT_MATCH(HttpStatus.BAD_REQUEST.value(), "confirm-password-does-not-match"),
    FAILED(HttpStatus.BAD_REQUEST.value(), "failed"),
    UNAUTHENTICATED(HttpStatus.BAD_REQUEST.value(), "unauthenticated"),
    UNAUTHORIZED(HttpStatus.BAD_REQUEST.value(), "unauthorized"),
    INVALID_REFRESH_TOKEN(HttpStatus.BAD_REQUEST.value(), "invalid-refresh-token")
    ;
    private final int code;
    private final String message;

    ErrorMessage(int i, String s) {
        this.code = i;
        this.message = s;
    }
}
