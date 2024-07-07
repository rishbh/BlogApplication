package com.blogapplication.blog.controllers;


import com.blogapplication.blog.config.AppConstants;
import com.blogapplication.blog.entities.APIResponse;
import com.blogapplication.blog.entities.ImageResponse;
import com.blogapplication.blog.services.implementation.FileServiceImpl;
import com.blogapplication.blog.services.implementation.postServiceImpl;
import com.blogapplication.blog.payloads.postDTO;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import static org.aspectj.weaver.tools.cache.SimpleCacheFactory.path;

@RestController
@RequestMapping("/apis/")
public class postController {

    @Autowired
    private postServiceImpl postService_instance;

    @Autowired
    private FileServiceImpl fileServiceImpl;

    @Value("${project.image}")
    String ImageFolderPath ;
    // create a post by userId and categoryId
    @PostMapping("/user/{userId}/category/{categoryId}/posts/")
    public ResponseEntity<postDTO> createPost(@RequestBody postDTO userPostdto
           ,@PathVariable Integer userId, @PathVariable Integer categoryId

    ){
       return new ResponseEntity<>(this.postService_instance.createPost(userPostdto,userId,categoryId), HttpStatus.CREATED);
    }

     // Get all the posts by userId
    @GetMapping("/user/{userId}/posts/")
    public ResponseEntity<List<postDTO>> getAllPostsByUser(@PathVariable Integer userId) {
        return new ResponseEntity<>(this.postService_instance.getPostsByUser(userId),HttpStatus.ACCEPTED);
    }

    //Get all Posts  by the category
    @GetMapping("/category/{categoryId}/posts/")
    public ResponseEntity<List<postDTO>>getAllPostsByCategory(@PathVariable Integer categoryId){
           return new ResponseEntity<>(this.postService_instance.getPostsByCategory(categoryId),HttpStatus.ACCEPTED);
    }

   //  Get all posts by userId and CategoryId both
    @GetMapping("/user/{userId}/category/{categoryId}/posts/")
    public ResponseEntity<List<postDTO>> getAllPostsByUSerAndCategories(@PathVariable Integer userId,@PathVariable Integer categoryId){
        System.out.println("Come at postCoontroller");
        return new ResponseEntity<>(this.postService_instance.getPostsByUserAndCategory(userId,categoryId),HttpStatus.ACCEPTED);
    }

    public ResponseEntity<postDTO>updatePost(@RequestBody postDTO userPostDTO,
                                             @PathVariable Integer userId,@PathVariable
                                             Integer categoryId){


                return null;
    }

    @GetMapping("/posts/")
    public ResponseEntity<List<postDTO>>getALlPosts(
            @RequestParam(value="pageNumber",defaultValue= AppConstants.PAGE_NUMBER,required = false)Integer pageNumber,
            @RequestParam(value="pageSize",defaultValue=AppConstants.PAGE_SIZE,required=false) Integer pageSize,
            @RequestParam(value="sortBy",defaultValue=AppConstants.SORT_BY,required=false) String sortingCriteria,
            @RequestParam(value="orderBy",defaultValue=AppConstants.ORDER_BY,required=false) String orderBy

    ){

           return new ResponseEntity<>(this.postService_instance.getAllPosts(pageNumber,pageSize,sortingCriteria,orderBy),HttpStatus.ACCEPTED);
    }
    @GetMapping("/posts/{postId}")
    public ResponseEntity<postDTO>getPostBypostId(@PathVariable Integer postId){
           return new ResponseEntity<>(this.postService_instance.getPostById(postId),HttpStatus.ACCEPTED);

    }

    @DeleteMapping("/posts/{postId}")
    public APIResponse deletePost(@PathVariable Integer postId){

           this.postService_instance.deletePost(postId);
           return new APIResponse("Post with"+postId+" got deleted ",true);
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<postDTO> updatePost(@RequestBody postDTO updatedPost,@PathVariable Integer postId){

           postDTO savedPost=   this.postService_instance.updatePost(updatedPost,postId);
           return new ResponseEntity<>(savedPost,HttpStatus.valueOf(200));


    }
    @GetMapping("/posts/search/{keywords}")
    public ResponseEntity<List<postDTO>> searchPostsByTitle(
            @PathVariable String keywords
    ){
        return new ResponseEntity<>(this.postService_instance.searchPostsByTitle(keywords), HttpStatus.valueOf(200));
    }

    @GetMapping("/posts/search/description/{keywords}")
    public ResponseEntity<List<postDTO>> searchPostsByDescription(
            @PathVariable String keywords
    ){
        return new ResponseEntity<>(this.postService_instance.searchPostsByDescription(keywords), HttpStatus.valueOf(200));
    }

    @PostMapping("/post/image/upload/{postId}")
    public ResponseEntity<postDTO> uploadImage(@RequestParam("image") MultipartFile uploadedImage ,
                            @PathVariable Integer postId) throws IOException {

           postDTO oldPostDTO= this.postService_instance.getPostById(postId);
           String uploadedImageFileName = this.fileServiceImpl.uploadImage(this.ImageFolderPath,uploadedImage);
           System.out.println("The uploaded image Filename is "+uploadedImageFileName);
            oldPostDTO.setImageName(uploadedImageFileName);
        System.out.println("The oldPostDTO image Filename is "+oldPostDTO.getImageName());
           postDTO updatedPost=this.postService_instance.updatePost(oldPostDTO,postId);
        System.out.println("The database image Filename is "+ updatedPost.getImageName());
           return new ResponseEntity<>(updatedPost,HttpStatus.ACCEPTED);
    }

    @GetMapping(value="/post/image/{imageName}",produces= MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(@PathVariable("imageName") String imageName,
                              HttpServletResponse response) throws IOException {

        InputStream ipstr= this.fileServiceImpl.getResource(this.ImageFolderPath,imageName);
         response.setContentType(MediaType.IMAGE_JPEG_VALUE);
         StreamUtils.copy(ipstr,response.getOutputStream());

    }
    
}
