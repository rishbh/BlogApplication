package com.blogapplication.blog.repositories;

import com.blogapplication.blog.entities.category;
import com.blogapplication.blog.payloads.categoryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface categoryRepo extends JpaRepository<category,Integer> {




}
