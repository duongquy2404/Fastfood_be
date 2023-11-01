package com.example.fastfood.repository;

import com.example.fastfood.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findCategoryByName(String name);
}
