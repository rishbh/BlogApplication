package com.blogapplication.blog.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@NoArgsConstructor
@Setter
@Getter
public class role {

       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       private Integer role_id;

       private String role_name;

}
