package com.dhruv.blogapp.exceptions;

import org.springframework.http.HttpStatus;

public class BlogAPIException extends RuntimeException{
    private HttpStatus httpStatus;
    private String Message;

    public BlogAPIException(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        Message = message;
    }

    public BlogAPIException(String message, HttpStatus httpStatus, String message1) {
        super(message);
        this.httpStatus = httpStatus;
        Message = message1;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return Message;
    }
}
