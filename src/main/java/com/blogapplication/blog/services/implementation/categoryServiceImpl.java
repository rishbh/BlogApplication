package com.blogapplication.blog.services.implementation;

import com.blogapplication.blog.entities.category;
import com.blogapplication.blog.exceptions.ResourceNotFoundException;
import com.blogapplication.blog.payloads.categoryDTO;
import com.blogapplication.blog.repositories.categoryRepo;
import com.blogapplication.blog.services.categoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class categoryServiceImpl implements categoryService {


   @Autowired
   private categoryRepo categoryRepo1;

   @Autowired
   private ModelMapper modelMapper;


   public categoryDTO createCategory(categoryDTO  c1){
          category newCategory = this.modelMapper.map(c1,category.class);
          category savedCategory=this.categoryRepo1.save(newCategory);

          categoryDTO savedCategoryDTO= this.modelMapper.map(savedCategory,categoryDTO.class);

          return savedCategoryDTO;
   }
   public categoryDTO updateCategory(categoryDTO newCategoryDTO,Integer oldCategoryID){

          category toUpdateCategory=this.categoryRepo1.findById(oldCategoryID).
                  orElseThrow(()->new ResourceNotFoundException(
                          "category","categoryID",oldCategoryID
                  ));

          toUpdateCategory.setTitle(newCategoryDTO.getTitle());
          toUpdateCategory.setDescription(newCategoryDTO.getDescription());

          return this.modelMapper.map(toUpdateCategory,categoryDTO.class);
   }

    @Override
    public List<categoryDTO> getAllCategories() {
           List<categoryDTO> allCategories=this.categoryRepo1.findAll().stream().map(c1->
               this.modelMapper.map(c1,categoryDTO.class)).collect(Collectors.toList());
           return allCategories;
    }

    public categoryDTO getCategory(Integer categoryId){
          category foundCategory=this.categoryRepo1.findById(categoryId).orElseThrow(()->
                  new ResourceNotFoundException("category","categoryId ",categoryId));
         categoryDTO foundCategoryDTO= this.modelMapper.map(foundCategory,categoryDTO.class);
         return foundCategoryDTO;
   }
   @Override
   public void deleteCategory(Integer categoryId){
       category foundCategory=this.categoryRepo1.findById(categoryId).orElseThrow(()->
               new ResourceNotFoundException("category","categoryId ",categoryId));

       this.categoryRepo1.delete(foundCategory);
       return ;

   }





}
