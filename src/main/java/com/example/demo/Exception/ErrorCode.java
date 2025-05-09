package com.example.demo.Exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ErrorCode {
    UNAUTHENTICATED("Unauthenticated!", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED("Unauthorized!", HttpStatus.FORBIDDEN),
    UNCATEGORIZED_EXCEPTION("Uncategorized!", HttpStatus.INTERNAL_SERVER_ERROR),
    BLANK_VALUE("Value not specified!", HttpStatus.BAD_REQUEST),
    USER_EXISTED("User existed!", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED("User not existed!", HttpStatus.NOT_FOUND),
    INVALID_KEY("Invalid message key!", HttpStatus.BAD_REQUEST),
    INVALID_USERNAME("Invalid username!", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD("Invalid password!", HttpStatus.BAD_REQUEST),
    INVALID_EMAIL("Invalid email!", HttpStatus.BAD_REQUEST),
    INVALID_PHONE_NUMBER("Invalid phone number!", HttpStatus.BAD_REQUEST),
    PRODUCT_NOT_EXISTED("Product not existed!", HttpStatus.BAD_REQUEST),
    SAVING_IMAGE_FAILED("Failed to save image!", HttpStatus.INTERNAL_SERVER_ERROR);

    final String msg;
    final HttpStatusCode httpStatusCode;

    ErrorCode(String msg, HttpStatusCode httpStatusCode) {
        this.msg = msg;
        this.httpStatusCode = httpStatusCode;
    }
}
