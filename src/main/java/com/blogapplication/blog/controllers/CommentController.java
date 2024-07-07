package com.blogapplication.blog.controllers;

import com.blogapplication.blog.entities.APIResponse;
import com.blogapplication.blog.payloads.commentDTO;
import com.blogapplication.blog.services.commentService;
import com.blogapplication.blog.services.postService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/apis")
public class CommentController {

    @Autowired
    private postService postS1;

    @Autowired
    private commentService c1;

    @PostMapping("/posts/{postId}/comments/")
    public ResponseEntity<commentDTO> createComment(@RequestBody commentDTO comment ,
                                                    @PathVariable Integer postId){
                commentDTO createdComment = this.c1.addComment(comment,postId);
                return new ResponseEntity<>(createdComment, HttpStatus.CREATED);

    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<APIResponse> deletComment(@PathVariable Integer commentId ){
           this.c1.deleteComment(commentId);
           return new ResponseEntity( new APIResponse("Comment with comment ID "+commentId+" is deleted successfully",true) , HttpStatus.ACCEPTED);
    }

    
}
