package com.blogapplication.blog.entities;

import com.blogapplication.blog.payloads.UserDTO;
import com.blogapplication.blog.payloads.categoryDTO;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jdk.jfr.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="posts")
public class Post {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer postId;

    @NotNull
    @Size(min=5)
    private String title;

    @NotNull
    @Size(min=40)
    @Column(name="content" ,length=1000)
    private String description;


    private Date addedDate;
    private String imageName;



    @ManyToOne
    @JoinColumn(name="postUserId")
    private User postUser;

    @ManyToOne
    @JoinColumn(name="postCategoryId")
    private category postCategory;

}
