package com.dhruv.blogapp.service.impl;

import com.dhruv.blogapp.exceptions.ResourceNotFoundException;
import com.dhruv.blogapp.model.Blog;
import com.dhruv.blogapp.payload.BlogDto;
import com.dhruv.blogapp.payload.BlogResponse;
import com.dhruv.blogapp.repositories.BlogRepo;
import com.dhruv.blogapp.service.BlogService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    private ModelMapper modelMapper;

    private BlogRepo blogRepo;

    public BlogServiceImpl(BlogRepo blogRepo, ModelMapper modelMapper) {
        this.blogRepo = blogRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public BlogDto createBlogpost(BlogDto blogDto) {
        Blog blog = DTOtoEntity(blogDto);

        Blog blog1 = blogRepo.save(blog);
        BlogDto blogDto1 = EntitytoDTO(blog1);

        return blogDto1;
    }

    @Override
    public BlogResponse getBlogposts(int pageNo, int pageSize, String property, String sortOrder) {

        Sort sort = Sort.by(property).ascending();
        if(sortOrder.equalsIgnoreCase(Sort.Direction.DESC.name())){
            sort = Sort.by(property).descending();
        } else if(sortOrder.equalsIgnoreCase(Sort.Direction.ASC.name())){
            sort = Sort.by(property).ascending();
        }
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Blog> blogPage = blogRepo.findAll(pageable);
        List<Blog> blogList = blogPage.getContent();
        List<BlogDto> responseBlogs = new ArrayList<>();
        for (int i = 0; i < blogList.size(); i++){
            BlogDto dto = EntitytoDTO(blogList.get(i));
            responseBlogs.add(dto);
        }

        BlogResponse blogResponse = new BlogResponse(responseBlogs,
                blogPage.getNumber(),
                blogPage.getSize(),
                blogPage.getTotalElements(),
                blogPage.getTotalPages(),
                blogPage.isLast());

        return blogResponse;
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

    private Blog DTOtoEntity(BlogDto dto){
        Blog blog = modelMapper.map(dto, Blog.class);
        return blog;
    }

    private BlogDto EntitytoDTO(Blog blog){
        BlogDto blogDto = modelMapper.map(blog, BlogDto.class);
        return blogDto;
    }

}
