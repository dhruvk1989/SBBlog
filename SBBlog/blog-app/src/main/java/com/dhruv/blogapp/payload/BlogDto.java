package com.dhruv.blogapp.payload;

import lombok.Data;

import java.util.Set;

@Data
public class BlogDto {
    private long id;
    private String title;
    private String description;
    private String content;
    private Set<CommentDto> comment;
}
