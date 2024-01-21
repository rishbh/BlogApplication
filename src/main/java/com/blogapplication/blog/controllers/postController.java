package com.blogapplication.blog.controllers;


import com.blogapplication.blog.payloads.postDTO;
import com.blogapplication.blog.services.implementation.postServiceImpl;
import com.blogapplication.blog.services.postService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/apis/")
public class postController {

    @Autowired
    private postServiceImpl postService_instance;
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<postDTO> createPost(@RequestBody postDTO userPostDTO
           ,@PathVariable Integer userId,@PathVariable Integer categoryId
    ){



        return new ResponseEntity<>(this.postService_instance.createPost(userPostDTO,userId,categoryId), HttpStatusCode.valueOf(200));
    }
}
