package com.abhrestaurant.clientserver.controller;


import com.abhrestaurant.clientserver.model.Category;
import com.abhrestaurant.clientserver.model.Meal;
import com.abhrestaurant.clientserver.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/allcategories")
    public List<Category> getAllCatefories(){

        System.out.println("Should be getted all categories");

        return categoryRepository.getAllCategories();
    }

    @PostMapping("/save/category")
    public Category saveCategory(@RequestBody Category category){

        System.out.println("Should be saved category: " + category.getName());

        return categoryRepository.save(category);

    }

    @DeleteMapping("delete/category/{categoryId}")
    public boolean deleteCategory(@PathVariable Long categoryId){
        categoryRepository.deleteById(categoryId);
        return true;
    }

    @GetMapping("/category/{categoryId}")
    public Category getCategoryById(@PathVariable Long categoryId){

        System.out.println("Should be getted category: "+ categoryId);
        return categoryRepository.getOne(categoryId);
    }
}
