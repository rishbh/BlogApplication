package com.blogapplication.blog.entities;

import lombok.Data;

@Data
public class JWTAuthRequest {
    String username;
    String password;
}
