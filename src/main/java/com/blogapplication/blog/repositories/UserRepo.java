package com.blogapplication.blog.repositories;

import com.blogapplication.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {

    //JPA Repository provides various functions

     public Optional<User> findByEmail(String email);




}
