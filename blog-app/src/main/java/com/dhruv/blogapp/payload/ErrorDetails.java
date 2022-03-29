package com.dhruv.blogapp.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class ErrorDetails {
    private Date date;
    private String message;
    private String details;

}
