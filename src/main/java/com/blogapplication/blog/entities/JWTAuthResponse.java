package com.blogapplication.blog.entities;

import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JWTAuthResponse {
    String token;
}
