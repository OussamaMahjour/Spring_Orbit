package com.faceboot.comment_service.controller;

import com.faceboot.comment_service.dto.CommentRequestDTO;
import com.faceboot.comment_service.dto.CommentResponseDTO;
import com.faceboot.comment_service.entity.Comment;
import com.faceboot.comment_service.model.Post;
import com.faceboot.comment_service.repository.CommentRepository;
import com.faceboot.comment_service.service.CommentService;
import com.faceboot.comment_service.service.CommentServiceInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/comment")
public class CommentController {

    public CommentController(CommentServiceInterface serviceInterface) {
        this.commentServiceInterface = serviceInterface;
    }

    private final CommentServiceInterface commentServiceInterface;

    @GetMapping("/all")
    public List<CommentResponseDTO> test() {
        return commentServiceInterface.getAllComments();
    }

    @DeleteMapping("/delete/{id}")
    public  String delete(@PathVariable("id") String id) {
        return commentServiceInterface.deleteCommentById(id);
    }

    @PostMapping("/add")
    public CommentResponseDTO add( @RequestBody  CommentRequestDTO commentRequest) {
        return commentServiceInterface.addComment(commentRequest);
    }

    @DeleteMapping("/delete/byPost/{postId}")
    public String deleteByPost(@PathVariable("postId") String postId) {
        return commentServiceInterface.deleteCommentsByPostId(postId);
    }

    @GetMapping("/byPost/{postId}")
    public List<CommentResponseDTO> byPost(@PathVariable("postId") String postId) {
        return commentServiceInterface.getAllCommentsByPostId(postId);
    }



}
