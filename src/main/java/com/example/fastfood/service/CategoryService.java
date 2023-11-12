package com.example.fastfood.service;

import com.example.fastfood.model.entity.Category;
import java.util.List;

public interface CategoryService {
    List<Category> getAllCategory();

    Category getCategoryById(Long id);
    boolean addNewCategory(Category category);

    Category updateCategory(Long id, Category category);

    boolean deleteCategory(Long id);
}
