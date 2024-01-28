package com.blogapplication.blog.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Users")
@NoArgsConstructor
@Getter
@Setter
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	private Integer id;

	@NotEmpty
	@Size(min=2 ,message="Name should be at least 2 characters long ")
	private String name;

	@Email
	private String email;
	@NotEmpty
	@Size(min=6,max=10,message="Password length should lie between 6 and 10")
	private String password;

	@NotEmpty
	private String about;

	 //JPA fetches all the posts automatically
	@OneToMany(mappedBy = "postUser",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private List<Post> allPosts=new ArrayList<>();

	
}
