package com.blogapplication.blog.repositories;

import com.blogapplication.blog.entities.Post;
import com.blogapplication.blog.entities.User;
import com.blogapplication.blog.entities.category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface postRepo extends JpaRepository<Post,Integer> {


    //   List<Post> findByUser(User postUser);
     //  List<Post> findByCategory(category category_instance);


}
