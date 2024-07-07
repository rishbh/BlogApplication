package com.blogapplication.blog.repositories;

import com.blogapplication.blog.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface commentRepository extends JpaRepository<Comment,Integer> {


}
