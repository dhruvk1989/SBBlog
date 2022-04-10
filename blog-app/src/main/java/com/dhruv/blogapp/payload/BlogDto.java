package com.dhruv.blogapp.payload;

import com.dhruv.blogapp.utils.AppConstants;
import lombok.Data;

import javax.validation.constraints.*;
import java.util.Set;

@Data
public class BlogDto {
    private long id;
    @NotNull
    @NotEmpty
    @Size(min = 5, message = "Title needs at least 5 characters")
    private String title;
    @Size(min = 5, message = "Title needs at least 5 characters")
    private String description;
    private String content;
    private Set<CommentDto> comment;
}
