package com.blogapplication.blog;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication()
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
//@EnableJpaRepositories("com.blogapplication.blog.repositories")
//@ComponentScan("com.blogapplication")
public class BloggingApplication implements CommandLineRunner {

	@Autowired
	PasswordEncoder passwordEncoder;
	public static void main(String[] args)  {

		SpringApplication.run(BloggingApplication.class, args);
	}

	@Bean
	public  ModelMapper modelMapper(){
      return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(this.passwordEncoder.encode("xyz"));
	}
}
