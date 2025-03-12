package com.example.demo.Exception;

import com.example.demo.Common.Error.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseException extends RuntimeException{
    private ErrorCode errorCode;
}
