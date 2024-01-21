package com.blogapplication.blog.controllers;

import com.blogapplication.blog.entities.APIResponse;
import com.blogapplication.blog.payloads.categoryDTO;
import com.blogapplication.blog.services.implementation.categoryServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apis/categories")
public class categoryController {

       @Autowired
       private categoryServiceImpl categoryService;

       @GetMapping("/{categoryId}")
       public ResponseEntity<categoryDTO> getCategory(@RequestBody @PathVariable Integer categoryId){
       return new ResponseEntity<>( this.categoryService.getCategory(categoryId),
               HttpStatusCode.valueOf(200));

       }

       @GetMapping("/")
       public ResponseEntity<List<categoryDTO>> getAllCategories(){
              return new ResponseEntity<>(this.categoryService.getAllCategories(), HttpStatusCode.valueOf(200));
       }

       @PostMapping("/")
       public ResponseEntity<categoryDTO> createCategory(@Valid @RequestBody categoryDTO newCategoryDTO){
           return new ResponseEntity<>(this.categoryService.createCategory(newCategoryDTO),HttpStatus.CREATED);
       }

       @PutMapping("/{categoryId}")
       public ResponseEntity<categoryDTO> updateCategory(@Valid @RequestBody categoryDTO newCategoryDTO,@PathVariable Integer categoryId){
              return new ResponseEntity<>(this.categoryService.updateCategory(newCategoryDTO,categoryId),HttpStatusCode.valueOf(200));
    }
       @DeleteMapping("/{categoryId}")
       public ResponseEntity<APIResponse> deleteCategory(@RequestBody @PathVariable Integer categoryId)
       {
           this.categoryService.deleteCategory(categoryId);
           APIResponse apiResponse=new APIResponse("Deleted successfully",true);
           return new ResponseEntity<>(apiResponse,HttpStatusCode.valueOf(200));
       }

}
