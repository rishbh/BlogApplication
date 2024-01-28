package com.blogapplication.blog.services;

import com.blogapplication.blog.payloads.UserDTO;

import java.util.List;

public interface userService {

    UserDTO createUser(UserDTO userDTO); // output should be respinse code
    UserDTO updateUser(UserDTO userDTO,Integer id);
    List<UserDTO> getAllUsers();
    UserDTO getUserById(Integer userId);
    void deleteUser(Integer userId);


}
