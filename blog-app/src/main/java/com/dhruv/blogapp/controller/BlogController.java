package com.dhruv.blogapp.controller;

import com.dhruv.blogapp.model.Blog;
import com.dhruv.blogapp.payload.BlogDto;
import com.dhruv.blogapp.service.BlogService;
import com.dhruv.blogapp.service.impl.BlogServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blogposts")
public class BlogController {

    private BlogService blogService;

    @PostMapping
    public ResponseEntity<BlogDto> createBlogpost(@RequestBody BlogDto blogDto){
        return new ResponseEntity<BlogDto>(blogService.createBlogpost(blogDto),HttpStatus.CREATED);
    }

    @GetMapping
    public List<BlogDto> getBlogposts(){
        List<BlogDto> responseBlogs = blogService.getBlogposts();
        return responseBlogs;
    }

    @GetMapping("{id}")
    public ResponseEntity<BlogDto> getBlogpost(@PathVariable Long id){
        BlogDto singleBlog = blogService.getBlogpost(id);
        return ResponseEntity.ok(singleBlog);
    }

    @PutMapping("{id}")
    public ResponseEntity<BlogDto> updateBlogpost(@PathVariable Long id, @RequestBody BlogDto blogDto){
        BlogDto dto = blogService.updateBlogpost(id, blogDto);
        return new ResponseEntity<BlogDto>(dto, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteBlogpost(@PathVariable Long id){
        blogService.removeBlogpost(id);
        return new ResponseEntity<String>("Post has been deleted", HttpStatus.OK);
    }

}
