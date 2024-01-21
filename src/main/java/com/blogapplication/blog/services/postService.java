package com.blogapplication.blog.services;

import com.blogapplication.blog.entities.category;
import com.blogapplication.blog.payloads.postDTO;

import java.util.List;

public interface postService {

       public postDTO createPost(postDTO userPost,Integer userId,Integer categoryId);
       public postDTO updatePost(postDTO newPost,Integer oldPostId);

       //all get posts methods
       public List<postDTO> getAllPosts();
       public postDTO getPostById(Integer postId);
       public List<postDTO> getPostsByCategory(category c1);
       public List<postDTO> searchPosts(String keyword);


       public void deletePost(Integer postId);

}
