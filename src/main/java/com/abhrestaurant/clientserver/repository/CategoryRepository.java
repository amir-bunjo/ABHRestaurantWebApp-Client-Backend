package com.abhrestaurant.clientserver.repository;

import com.abhrestaurant.clientserver.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value="SELECT * FROM category ORDER BY id DESC", nativeQuery = true)
    List<Category> getAllCategories();
}
