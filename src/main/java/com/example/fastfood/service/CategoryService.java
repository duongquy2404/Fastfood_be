package com.example.fastfood.service;

import com.example.fastfood.model.entity.Category;
import java.util.List;

public interface CategoryService {
    List<Category> getAllCategory();

    Category getCategoryById(Integer id);
    boolean addNewCategory(Category category);

    Category updateCategory(Integer id, Category category);

    boolean deleteCategory(Integer id);
}
