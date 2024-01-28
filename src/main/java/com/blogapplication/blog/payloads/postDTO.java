package com.blogapplication.blog.payloads;

import com.blogapplication.blog.entities.User;
import com.blogapplication.blog.entities.category;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class postDTO {


    private String title;
    private String description;

    private String addedDate;
    private String imageName;

    private UserDTO postUser;

    private categoryDTO postCategory;


}
