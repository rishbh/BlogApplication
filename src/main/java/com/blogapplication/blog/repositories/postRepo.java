package com.blogapplication.blog.repositories;

import com.blogapplication.blog.entities.Post;
import com.blogapplication.blog.entities.User;
import com.blogapplication.blog.entities.category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface postRepo extends JpaRepository<Post,Integer> , PagingAndSortingRepository<Post,Integer> {

      //
       List<Post> findByPostUser(User postUser);
       List<Post> findByPostCategory(category category_instance);
            //https://www.baeldung.com/spring-data-jpa-findby-multiple-columns
       List<Post> findByPostUserAndPostCategory(User u,category c);
       List<Post> findByTitleContaining(String title);
       List<Post> findByDescriptionContaining(String description);


}
