//package com.blogapplication.blog.repositories;
//import com.blogapplication.blog.entities.category;
//import payloads.categoryDTO;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public interface categoryRepo extends JpaRepository<category,Integer> {
//}
//
//
//
//
package com.blogapplication.blog.repositories;

import com.blogapplication.blog.entities.category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface categoryRepo extends JpaRepository<category,Integer> {

    //JPA Repository provides various functions
          Optional<category> findById(Integer categoryId);



}
