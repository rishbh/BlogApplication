package com.blogapplication.blog.controllers;


import com.blogapplication.blog.services.implementation.userServiceImpl;
import com.blogapplication.blog.entities.APIResponse;
import com.blogapplication.blog.payloads.UserDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/apis/users")
public class UserController {

       @Autowired
       private userServiceImpl userService;

       @PostMapping("/")
       public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO){

              System.out.println(" Getting userDTO as "+(userDTO.getId())
               +" "+(userDTO.getName())+" " +(userDTO.getEmail())+" "+
                      (userDTO.getPassword())+" "+(userDTO.getAbout())



              );
              UserDTO createdUser=this.userService.createUser(userDTO);
              return new ResponseEntity<>(createdUser, HttpStatus.CREATED);

       }
       @PutMapping("/{userId}")
       public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO , @PathVariable Integer userId ){

              UserDTO updatedUser=this.userService.updateUser(userDTO,userId);
              return ResponseEntity.ok(updatedUser);

       }
       @DeleteMapping("/{userId}")
       public ResponseEntity<APIResponse> deleteUser(@RequestBody @PathVariable Integer userId){

              this.userService.deleteUser(userId);
              return new ResponseEntity(new APIResponse(" All done ",true),HttpStatusCode.valueOf(200));
       }
       @GetMapping("/")
       public ResponseEntity<List<UserDTO>> getAllUsers(){
              System.out.println("Hello");
              return new ResponseEntity(this.userService.getAllUsers(),HttpStatusCode.valueOf(200));
       }

       @GetMapping("/{userId}")
       public ResponseEntity<UserDTO>getUSerById(@RequestBody @PathVariable Integer userId){

              return new ResponseEntity(this.userService.getUserById(userId),HttpStatusCode.valueOf(200));
       }




}
