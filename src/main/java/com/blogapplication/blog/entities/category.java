package com.blogapplication.blog.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class category {

                 @Id
                 @GeneratedValue(strategy= GenerationType.IDENTITY)
              private   Integer categoryId;


          @NotNull
          @Size(min=4 ,message = "Minimum size is 4 for title")
          private String title;

          @Size(min =10 ,max = 900)
          private String description;

          @OneToMany(mappedBy = "postCategory" , cascade = CascadeType.ALL ,fetch = FetchType.LAZY)
          private List<Post> allposts=new ArrayList<>();

}
