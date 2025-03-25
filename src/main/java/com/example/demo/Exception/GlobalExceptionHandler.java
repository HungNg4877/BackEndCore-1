package com.example.demo.Exception;

import com.example.demo.DTO.Response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    // Xử lý lỗi chung do hệ thống
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleGeneralException(Exception e) {
        log.error("Unexpected error occurred: ", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(500, "Internal Server Error", null));
    }

    // Xử lý lỗi custom từ hệ thống
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ApiResponse<Object>> handlerBaseException(BaseException e) {
        log.error("Business Exception: {}", e.getMessage());
        return ResponseEntity.status(HttpStatusCode.valueOf(e.getErrorMessage().getCode()))
                .body(new ApiResponse<>(e.getErrorMessage().getCode(), e.getErrorMessage().getMessage(), null));
    }

    // Xử lý lỗi Validation (Ví dụ: @Valid hoặc @RequestParam không hợp lệ)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidationException(MethodArgumentNotValidException e) {
        String errorMessage = "Validation failed!";
        if (e.getBindingResult().hasErrors()) {
            errorMessage = e.getBindingResult().getAllErrors().getFirst().getDefaultMessage();
        }
        return ResponseEntity.badRequest().body(new ApiResponse<>(400, errorMessage, null));
    }

    // Xử lý lỗi BindException (Thường xảy ra khi request parameter bị sai format)
    @ExceptionHandler(BindException.class)
    public ResponseEntity<ApiResponse<Object>> handlerBindException(BindException e) {
        String errorMessage = "Invalid request!";
        if (e.getBindingResult().hasErrors()) {
            errorMessage = e.getBindingResult().getAllErrors().getFirst().getDefaultMessage();
        }
        return ResponseEntity.badRequest().body(new ApiResponse<>(400, errorMessage, null));
    }

    // Xử lý lỗi khi request có JSON sai format hoặc không thể đọc
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<Object>> handleJsonParseException(HttpMessageNotReadableException e) {
        log.error("Invalid JSON format: {}", e.getMessage());
        return ResponseEntity.badRequest()
                .body(new ApiResponse<>(400, "Invalid JSON format or request body", null));
    }

    // Xử lý lỗi khi không tìm thấy tài nguyên
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleResourceNotFoundException(ResourceNotFoundException e) {
        log.warn("Resource not found: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(404, e.getMessage(), null));
    }

    // Xử lý lỗi không có quyền truy cập (Unauthorized)
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResponse<Object>> handleAccessDeniedException(AccessDeniedException e) {
        log.warn("Access denied: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new ApiResponse<>(403, "Access Denied", null));
    }

    // Xử lý lỗi khi gọi API bị conflict (VD: email đã tồn tại)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse<Object>> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        log.warn("Database Integrity Violation: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ApiResponse<>(409, "Data conflict, possible duplication!", null));
    }
}

