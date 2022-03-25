package com.dhruv.blogapp.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogResponse {
    private List<BlogDto> blogContent;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last; //check if this page is last.

}
