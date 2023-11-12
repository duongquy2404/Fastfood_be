package com.example.fastfood.service.impl;

import com.example.fastfood.model.entity.Category;
import com.example.fastfood.repository.CategoryRepository;
import com.example.fastfood.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public boolean addNewCategory(Category category) {
        if(categoryRepository.findCategoryByName(category.getName()) != null){
            return false;
        }else{
            categoryRepository.save(category);
            return true;
        }
    }

    @Override
    public Category updateCategory(Long id, Category category) {
        Category find = categoryRepository.findById(id).orElse(null);
        if(find != null){
            find.setName(category.getName());
            return categoryRepository.save(find);
        }else return null;
    }

    @Override
    public boolean deleteCategory(Long id) {
        if(categoryRepository.findById(id).orElse(null) != null) {
            categoryRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
