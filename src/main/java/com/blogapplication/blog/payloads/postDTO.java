package com.blogapplication.blog.payloads;

import com.blogapplication.blog.entities.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class postDTO {

    private Integer postId;
    private String title;
    private String description;

    private Date addedDate;
    private String imageName;

    private UserDTO postUser;

    private categoryDTO postCategory;

    private Set<commentDTO> comments=new HashSet<>();

}
