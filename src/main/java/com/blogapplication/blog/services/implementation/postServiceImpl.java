package com.blogapplication.blog.services.implementation;

import com.blogapplication.blog.entities.Post;
import com.blogapplication.blog.entities.User;
import com.blogapplication.blog.entities.category;
import com.blogapplication.blog.exceptions.ResourceNotFoundException;
import com.blogapplication.blog.payloads.postDTO;
import com.blogapplication.blog.repositories.UserRepo;
import com.blogapplication.blog.repositories.categoryRepo;
import com.blogapplication.blog.repositories.postRepo;
import com.blogapplication.blog.services.postService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
           postEntity.setAddedDate(new Date());
           postEntity.setImageName("basicImage.png");

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
           oldPost.setImageName(newPost.getImageName());

           Post savedPost=this.post_repo_instance.save(oldPost);
           postDTO savedPostDTO=this.modelMapper_instance.map(savedPost,postDTO.class);
           return savedPostDTO ;
    }

    @Override
    public List<postDTO> getAllPosts(Integer pageNumber,Integer pageSize ,
                                     String sortCriteria ,String orderBy) {
         Sort sortObj=null;
         if(orderBy.equals("descending")){
             sortObj= Sort.by(sortCriteria).descending();
         }else{
             sortObj=Sort.by(sortCriteria).ascending();
        }
        //springframework.data.domain ka pageable lena hain humne
        Pageable p=  PageRequest.of(pageNumber,pageSize,sortObj);
        Page<Post>page= this.post_repo_instance.findAll(p);
        List<Post>posts =                page.getContent();
        return posts.stream().map(post->{
            return this.modelMapper_instance.map(post,postDTO.class);
        }
        ).collect(Collectors.toList());

    }

    @Override
    public postDTO getPostById(Integer postId) {

        return this.modelMapper_instance.map(this.post_repo_instance.findById(postId).orElseThrow(()->{
            return new ResourceNotFoundException("Post","PostId",postId);
        }),postDTO.class);
    }

    @Override
    public List<postDTO> getPostsByCategory(Integer categoryId) {

        category c1=this.category_repo.findById(categoryId).orElseThrow(()->{return new ResourceNotFoundException("Category","category Id",categoryId);});
        List<Post>postsByCategories= this.post_repo_instance.findByPostCategory(c1);
        return  postsByCategories.stream().map(post->{return this.modelMapper_instance.map(post,postDTO.class);}).collect(Collectors.toList());

    }

    @Override
    public List<postDTO> getPostsByUser(Integer userId) {
           User user1=this.user_repo.findById(userId).orElseThrow(()->{return new ResourceNotFoundException("User ","userId",userId);});
           List<Post>postsByUser= this.post_repo_instance.findByPostUser(user1);
           return postsByUser.stream().map(post->{return this.modelMapper_instance.map(post,postDTO.class);}).collect(Collectors.toList());
    }
    public List<postDTO> getPostsByUserAndCategory(Integer user_id,Integer categoryId){
        System.out.println("Come at service impl of post");
        User user1=this.user_repo.findById(user_id).orElseThrow(()->{return new ResourceNotFoundException("User ","userId",user_id);});
        category c1=this.category_repo.findById(categoryId).orElseThrow(()->{return new ResourceNotFoundException("Category","category Id",categoryId);});
        List<Post>postsByCategoryAndUser=this.post_repo_instance.findByPostUserAndPostCategory(user1,c1);
        return postsByCategoryAndUser.stream().map(post->{
            return this.modelMapper_instance.map(post,postDTO.class);
        }).collect(Collectors.toList());
    }
    @Override
    public List<postDTO> searchPostsByTitle(String titleKeyword) {
           List<Post>allPosts_with_Titlekeyword = this.post_repo_instance.findByTitleContaining(titleKeyword);
           List<postDTO> allPosts=   allPosts_with_Titlekeyword.stream().map(post->{
               return this.modelMapper_instance.map(post, postDTO.class);
           }).collect(Collectors.toList());
           return allPosts;
    }
    @Override
    public List<postDTO> searchPostsByDescription(String descriptionKeyword) {
        List<Post>allPosts_with_Titlekeyword = this.post_repo_instance.findByDescriptionContaining(descriptionKeyword);
        List<postDTO> allPosts=   allPosts_with_Titlekeyword.stream().map(post->{
            return this.modelMapper_instance.map(post, postDTO.class);
        }).collect(Collectors.toList());
        return allPosts;
    }

    @Override
    public void deletePost(Integer postId) {
           Post post=this.post_repo_instance.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Post Id",postId));
           this.post_repo_instance.delete(post);
           return;
    }





}
