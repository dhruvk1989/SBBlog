package com.dhruv.blogapp.controller;

import com.dhruv.blogapp.model.Comment;
import com.dhruv.blogapp.payload.CommentDto;
import com.dhruv.blogapp.payload.CommentResponse;
import com.dhruv.blogapp.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/blogposts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId") long postId,
                                                    @Valid @RequestBody CommentDto commentDto) {
        return new ResponseEntity<CommentDto>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
    }

    @GetMapping("/blogposts/{postId}/comments")
    public CommentResponse getComments(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                       @RequestParam(value = "size", required = false, defaultValue = "10") int size,
                                       @RequestParam(value = "sortBy", required = false, defaultValue = "id") String property,
                                       @RequestParam(value = "orderBy", required = false, defaultValue = "ASC") String orderBy,
                                       @PathVariable(value = "postId") long postId){

        CommentResponse allComments = commentService.getAllComments(postId, size, page, property, orderBy);
        return allComments;

    }

    @GetMapping("/blogposts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> getComment(@PathVariable(value = "postId") int postId,
                                                 @PathVariable(value = "commentId") int commentId){
        return new ResponseEntity<CommentDto>(commentService.getComment(postId, commentId), HttpStatus.OK);
    }

    @PutMapping("/blogposts/{postId}/comments/{commentId}")
    public CommentDto updateComment(@PathVariable(value = "postId") long postId,
                                    @PathVariable(value="commentId") Long commentId,
                                    @Valid @RequestBody CommentDto comment){
        CommentDto commentDto = commentService.updateComment(postId, commentId, comment);
        return commentDto;
    }

    @DeleteMapping("/blogposts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> deleteComment(@PathVariable(value = "postId") long postId,
                                                    @PathVariable(value="commentId") Long commentId) {
        return new ResponseEntity<CommentDto>(commentService.deleteComment(postId, commentId), HttpStatus.OK);
    }
}
