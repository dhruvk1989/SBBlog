package com.dhruv.blogapp.service;

import com.dhruv.blogapp.model.Blog;
import com.dhruv.blogapp.payload.BlogDto;

import java.util.List;

public interface BlogService {
    BlogDto createBlogpost(BlogDto blogDto);

    List<BlogDto> getBlogposts();

    BlogDto getBlogpost(Long id);

    BlogDto updateBlogpost(Long id, BlogDto blogDto);

    void removeBlogpost(Long id);
}
