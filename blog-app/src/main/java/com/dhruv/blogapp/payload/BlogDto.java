package com.dhruv.blogapp.payload;

import com.dhruv.blogapp.utils.AppConstants;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
public class BlogDto {
    private long id;
    @NotNull
    @NotEmpty
    @Max(value = 100)
    @Min(value = 5, message = "Title needs at least 5 characters")
    private String title;
    @Min(value = 5, message = "Title needs at least 5 characters")
    private String description;
    private String content;
    private Set<CommentDto> comment;
}
