package com.blogapplication.blog.controllers;


import com.blogapplication.blog.entities.JWTAuthRequest;
import com.blogapplication.blog.entities.JWTAuthResponse;
import com.blogapplication.blog.security.JWTtokenHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import com.blogapplication.blog.config.SecurityConfig.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class authController {

    @Autowired
    private JWTtokenHelper jwTtokenHelper;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    AuthenticationManager authenticationManager;
    @PostMapping("/login")
    public ResponseEntity<JWTAuthResponse> createToken(
            @RequestBody JWTAuthRequest request

            ) throws Exception
    {
         System.out.println(" the request that came is   "+request.getUsername()+" -> "+request.getPassword());
         this.authenticate(request.getUsername(),request.getPassword());
         System.out.println("Authenticated  successfully !! ");

         UserDetails userDetails1 = this.userDetailsService.loadUserByUsername(request.getUsername());
         System.out.println(userDetails1.getUsername()+" &password "+ userDetails1.getPassword());

         String generatedToken = this.jwTtokenHelper.generateToken(userDetails1);

         return new ResponseEntity<JWTAuthResponse>(new JWTAuthResponse(generatedToken), HttpStatusCode.valueOf(200)  );

    }

    private void authenticate(String username, String password) throws Exception {
        String userName = username;
        System.out.println("Username that came isa "+username);
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username,password);
        try{
            System.out.println(" doing authentication here !!");
            this.authenticationManager.authenticate(authToken);
        }catch(BadCredentialsException e){
            System.out.println(" Invalid USername or password !!");
            throw new Exception("Invalid username or Password ");

        }




    }


}
