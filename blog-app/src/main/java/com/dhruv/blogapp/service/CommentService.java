package com.dhruv.blogapp.service;

import com.dhruv.blogapp.payload.CommentDto;
import com.dhruv.blogapp.payload.CommentResponse;

import java.util.List;

public interface CommentService {

    CommentResponse getAllComments(long postId, int pageSize, int pageNo, String property, String sortOrder);

    CommentDto createComment(long postId, CommentDto comment);

    CommentDto getComment(long blogId, long commentId);

    CommentDto updateComment(long postId, long commentId, CommentDto comment);

    CommentDto deleteComment(long postId, long commentId);

}
