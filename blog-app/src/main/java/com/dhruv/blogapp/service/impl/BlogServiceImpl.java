package com.dhruv.blogapp.service.impl;

import com.dhruv.blogapp.exceptions.ResourceNotFoundException;
import com.dhruv.blogapp.model.Blog;
import com.dhruv.blogapp.payload.BlogDto;
import com.dhruv.blogapp.repositories.BlogRepo;
import com.dhruv.blogapp.service.BlogService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    private BlogRepo blogRepo;

    public BlogServiceImpl(BlogRepo blogRepo) {
        this.blogRepo = blogRepo;
    }

    @Override
    public BlogDto createBlogpost(BlogDto blogDto) {
        Blog blog = DTOtoEntity(blogDto);

        Blog blog1 = blogRepo.save(blog);
        BlogDto blogDto1 = EntitytoDTO(blog1);

        return blogDto1;
    }

    @Override
    public List<BlogDto> getBlogposts() {
        List<Blog> blogs = blogRepo.findAll();
        List<BlogDto> responseBlogs = new ArrayList<>();
        for (int i = 0; i < blogs.size(); i++){
            BlogDto dto = EntitytoDTO(blogs.get(i));
            responseBlogs.add(dto);
        }
        return responseBlogs;
    }

    @Override
    public BlogDto getBlogpost(Long id) {

        Blog blog = blogRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        return EntitytoDTO(blog);
    }

    @Override
    public BlogDto updateBlogpost(Long id, BlogDto blogDto) {
        Blog blog = blogRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        blog.setTitle(blogDto.getTitle());
        blog.setDescription(blogDto.getDescription());
        blog.setContent(blogDto.getContent());
        return EntitytoDTO(blogRepo.save(blog));
    }

    @Override
    public void removeBlogpost(Long id) {
        Blog blog = blogRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        blogRepo.delete(blog);
    }

    private static Blog DTOtoEntity(BlogDto dto){
        Blog blog = new Blog();
        blog.setTitle(dto.getTitle());
        blog.setDescription(dto.getDescription());
        blog.setId(dto.getId());
        blog.setContent(dto.getContent());
        return blog;
    }

    private static BlogDto EntitytoDTO(Blog blog){
        BlogDto dto = new BlogDto();
        dto.setTitle(blog.getTitle());
        dto.setDescription(blog.getDescription());
        dto.setId(blog.getId());
        dto.setContent(blog.getContent());
        return dto;
    }

}
