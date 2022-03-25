package com.dhruv.blogapp.service;

import com.dhruv.blogapp.model.Blog;
import com.dhruv.blogapp.payload.BlogDto;
import com.dhruv.blogapp.payload.BlogResponse;

import java.util.List;

public interface BlogService {
    BlogDto createBlogpost(BlogDto blogDto);

    BlogResponse getBlogposts(int pageNo, int pageSize, String property, String sortOrder);

    BlogDto getBlogpost(Long id);

    BlogDto updateBlogpost(Long id, BlogDto blogDto);

    void removeBlogpost(Long id);
}
