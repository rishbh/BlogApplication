package com.blogapplication.blog.services;

import com.blogapplication.blog.entities.User;
import com.blogapplication.blog.entities.category;
import com.blogapplication.blog.payloads.postDTO;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface postService {

       public postDTO createPost(postDTO userPost, Integer userId, Integer categoryId);
       public postDTO updatePost(postDTO newPost,Integer oldPostId);

       //all get posts methods
       public List<postDTO> getAllPosts(Integer pageNumber, Integer pageSize, String sortCriteria,String orderBy);
       public postDTO getPostById(Integer postId);
       public List<postDTO> getPostsByCategory(Integer category_id);

       public List<postDTO> getPostsByUser(Integer user_id);

       public List<postDTO> getPostsByUserAndCategory(Integer user_id,Integer category_id);
       public List<postDTO> searchPostsByTitle(String titleKeyword);

       public List<postDTO> searchPostsByDescription(String descriptionKeyword);

       public void deletePost(Integer postId);

}
