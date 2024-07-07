package com.blogapplication.blog.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.repository.cdi.Eager;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
    @NotNull
//    @JoinColumn(name="postUserId")
    private User postUser;

    @ManyToOne
    @NotNull
   // @JoinColumn(name="postCategoryId")
    private category postCategory;


    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
    private Set<Comment> comments=new HashSet<>();
}
