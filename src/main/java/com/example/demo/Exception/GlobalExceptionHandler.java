package com.example.demo.Exception;

import com.example.demo.Dto.Response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ApiResponse<Object>> handlerBaseException(BaseException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatusCode.valueOf(e.getErrorCode().getCode()))
                .body(new ApiResponse<>(e.getErrorCode().getCode(), e.getErrorCode().getMessage(), null));
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ApiResponse<Object>> handlerBindException(BindException e) {
        String errorMessage = "invalid request!";
        if (e.getBindingResult().hasErrors()) {
            errorMessage = e.getBindingResult().getAllErrors().getFirst().getDefaultMessage();
        }

        return ResponseEntity.badRequest().body(new ApiResponse<>(400, errorMessage, null) );
    }
}
