package com.blogapplication.blog.repositories;

import com.blogapplication.blog.entities.User;
import com.blogapplication.blog.payloads.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {

    //JPA Repository provides various functions




}
