package com.blogapplication.blog.security;

import com.blogapplication.blog.exceptions.ResourceNotFoundException;
import com.blogapplication.blog.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(" Now seraching DB ");
     UserDetails user1= this.userRepo.findByEmail(username).orElseThrow(()->new ResourceNotFoundException("User","email"+username,0));
     System.out.println(user1.getUsername());
     return user1;
    }
}
