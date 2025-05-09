package com.example.demo.Exception;

import com.example.demo.DTO.Response.ResponseNorm;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptHandler {

    ResponseEntity<ResponseNorm<Void>> handlingResponse(ErrorCode errorCode) {
        return ResponseEntity
                .status(errorCode.getHttpStatusCode())
                .body(
                        ResponseNorm
                                .<Void>builder()
                                .msg(errorCode.getMsg())
                                .build()
                );
    }


    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ResponseNorm<Void>> handlingAppException(AppException exception) {
        ErrorCode errorCode = exception.getErrorCode();
        return handlingResponse(errorCode);
    }


    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ResponseNorm<Void>> handlingMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        ErrorCode errorCode = ErrorCode.INVALID_KEY;
        try {
            errorCode = ErrorCode.valueOf(exception.getFieldError().getDefaultMessage());
        }
        catch(Exception e) {}
        return handlingResponse(errorCode);
    }


    @ExceptionHandler(value = AuthorizationDeniedException.class)
    ResponseEntity<ResponseNorm<Void>> handlingAuthorizationDeniedException(AuthorizationDeniedException exception) {
        return handlingResponse(ErrorCode.UNAUTHORIZED);
    }


    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ResponseNorm<Void>> handlingUndefinedResponses(Exception exception) {
        ErrorCode errorCode = ErrorCode.UNCATEGORIZED_EXCEPTION;
        return handlingResponse(errorCode);
    }
}
