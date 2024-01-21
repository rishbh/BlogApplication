package com.blogapplication.blog.services.implementation;

import com.blogapplication.blog.entities.Post;
import com.blogapplication.blog.entities.User;
import com.blogapplication.blog.entities.category;
import com.blogapplication.blog.exceptions.ResourceNotFoundException;
import com.blogapplication.blog.payloads.UserDTO;
import com.blogapplication.blog.payloads.categoryDTO;
import com.blogapplication.blog.payloads.postDTO;
import com.blogapplication.blog.repositories.UserRepo;
import com.blogapplication.blog.repositories.categoryRepo;
import com.blogapplication.blog.repositories.postRepo;
import com.blogapplication.blog.services.postService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class postServiceImpl implements postService {

    @Autowired
    private postRepo post_repo_instance;
    @Autowired
    private ModelMapper modelMapper_instance;
    @Autowired
    private UserRepo user_repo;
    @Autowired
    private categoryRepo category_repo;



    @Override
    // conversion of postdto to psotEntity save it and returns postDTo
    public postDTO createPost(postDTO userPostDTO ,Integer userId,Integer categoryId) {
           Post postEntity = this.modelMapper_instance.map(userPostDTO , Post.class );
           postEntity.setImageName("default.png");
           postEntity.setAddedDate(new Date());


           User foundUser=this.user_repo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User ","userId ",userId));
           category foundCategory=this.category_repo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","category Id ",categoryId));

           postEntity.setPostUser(foundUser);
           postEntity.setPostCategory(foundCategory);

           Post saved_postEntity=this.post_repo_instance.save(postEntity);

           postDTO savedpostDTO=this.modelMapper_instance.map(saved_postEntity,postDTO.class);
           return savedpostDTO;
    }

    @Override
    public postDTO updatePost(postDTO newPostDTO, Integer oldPostId) {

           Post oldPost=this.post_repo_instance.findById(oldPostId).orElseThrow(()->new ResourceNotFoundException("Post ","postID",oldPostId));
           Post newPost=this.modelMapper_instance.map(newPostDTO,Post.class);

           oldPost.setTitle(newPost.getTitle());
           oldPost.setDescription(newPost.getDescription());
           oldPost.setAddedDate(new Date());

           Post savedPost=this.post_repo_instance.save(oldPost);
           postDTO savedPostDTO=this.modelMapper_instance.map(savedPost,postDTO.class);
           return savedPostDTO ;
    }

    @Override
    public List<postDTO> getAllPosts() {
        return null;
    }

    @Override
    public postDTO getPostById(Integer postId) {
        return null;
    }

    @Override
    public List<postDTO> getPostsByCategory(category c1) {
        return null;
    }

    @Override
    public List<postDTO> searchPosts(String keyword) {
        return null;
    }

    @Override
    public void deletePost(Integer postId) {

    }

}
