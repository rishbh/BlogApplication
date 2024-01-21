package com.blogapplication.blog.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class APIResponse {

    private String message;
    private Boolean isSucceed;

}
