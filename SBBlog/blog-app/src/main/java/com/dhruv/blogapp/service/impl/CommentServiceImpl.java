package com.dhruv.blogapp.service.impl;

import com.dhruv.blogapp.exceptions.BlogAPIException;
import com.dhruv.blogapp.exceptions.ResourceNotFoundException;
import com.dhruv.blogapp.model.Blog;
import com.dhruv.blogapp.model.Comment;
import com.dhruv.blogapp.payload.CommentDto;
import com.dhruv.blogapp.payload.CommentResponse;
import com.dhruv.blogapp.repositories.BlogRepo;
import com.dhruv.blogapp.repositories.CommentRepo;
import com.dhruv.blogapp.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private ModelMapper modelMapper;
    private CommentRepo commentRepo;
    private BlogRepo blogRepo;

    public CommentServiceImpl(CommentRepo commentRepo, BlogRepo blogRepo, ModelMapper modelMapper) {
        this.commentRepo = commentRepo;
        this.blogRepo = blogRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public CommentResponse getAllComments(long postId, int pageSize, int pageNo, String property, String sortOrder) {
        Sort sort = Sort.by(property).ascending();
        if(sortOrder.equalsIgnoreCase(Sort.Direction.ASC.name())){
            sort.ascending();
        } else if(sortOrder.equalsIgnoreCase(Sort.Direction.DESC.name())){
            sort.descending();
        }
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Comment> all = commentRepo.findAll(pageable);
        List<Comment> comments = all.getContent();
        List<CommentDto> commentDtos = new ArrayList<>();
        for (int i = 0; i < comments.size(); i++){
            commentDtos.add(EntitytoDTO(comments.get(i)));
        }

        CommentResponse response = new CommentResponse(commentDtos, all.getNumber(), all.getSize(),
                all.getTotalElements(), all.getTotalPages(), all.isLast());

        return response;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto comment) {
        Comment comment1 = DTOtoEntity(comment);
        Blog blog = blogRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        comment1.setBlog(blog);
        return EntitytoDTO(commentRepo.save(comment1));
    }

    @Override
    public CommentDto getComment(long postId, long commentId) {
        Blog blog = blogRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        Comment comment = commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));;
        if(!comment.getBlog().getId().equals(blog.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to any blogpost.");
        }
        return EntitytoDTO(comment);
    }

    @Override
    public CommentDto updateComment(long postId, long commentId, CommentDto comment) {
        Blog blog = blogRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        Comment comment1 = commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "commentId", commentId));
        if(comment1.getBlog().getId() == blog.getId()) {
            comment1.setName(comment.getName());
            comment1.setEmail(comment.getEmail());
            comment1.setComment(comment.getContent());
            return EntitytoDTO(commentRepo.save(comment1));
        }else{
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to this blogpost");
        }
    }

    @Override
    public CommentDto deleteComment(long postId, long commentId) {
        Blog blog = blogRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("blog", "id", postId));
        Comment comment = commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("comment", "id", commentId));
        if(!comment.getBlog().getId().equals(blog.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to this blogpost");
        }
        commentRepo.delete(comment);
        return EntitytoDTO(comment);
    }

    private Comment DTOtoEntity(CommentDto dto){
        Comment comment = modelMapper.map(dto, Comment.class);
        return comment;
    }

    private CommentDto EntitytoDTO(Comment comment){
        CommentDto dto = modelMapper.map(comment, CommentDto.class);
        return dto;
    }

}
