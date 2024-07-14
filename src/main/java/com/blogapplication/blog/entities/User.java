package com.blogapplication.blog.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name="Users")
@NoArgsConstructor
@Getter
@Setter
public class User implements UserDetails {

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

	@ManyToMany(cascade= CascadeType.ALL ,fetch = FetchType.EAGER)
	@JoinTable(
			name="user_roles",
			//current USer ka kya column jaega in combined table
			joinColumns = @JoinColumn(name="user_Id" , referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name="role_ID" , referencedColumnName = "role_id")
	)
	private Set<role> user_roles;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.user_roles.stream().map(  (role)-> new SimpleGrantedAuthority(role.getRole_name()))
				.collect(Collectors.toList());

	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
