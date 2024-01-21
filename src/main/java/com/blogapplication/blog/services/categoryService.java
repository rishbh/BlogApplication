package com.blogapplication.blog.services;

import com.blogapplication.blog.payloads.categoryDTO;

import java.util.List;

public interface categoryService {

    public categoryDTO createCategory(categoryDTO newCategoryDTO);
    public categoryDTO updateCategory(categoryDTO updatedCategory,Integer oldCategoryId);
    public categoryDTO getCategory(Integer categoryId);
    public List<categoryDTO> getAllCategories();
    public void deleteCategory(Integer categoryId);
}
