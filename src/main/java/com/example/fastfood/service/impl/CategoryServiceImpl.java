package com.example.fastfood.service.impl;

import com.example.fastfood.model.entity.Category;
import com.example.fastfood.repository.CategoryRepository;
import com.example.fastfood.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository repo;
    @Override
    public List<Category> getAllCategory() {
        return repo.findAll();
    }

    @Override
    public Category getCategoryById(Integer id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public boolean addNewCategory(Category category) {
        if(repo.findCategoryByName(category.getName()) != null){
            return false;
        }else{
            repo.save(category);
            return true;
        }
    }

    @Override
    public Category updateCategory(Integer id, Category category) {
        Category find = repo.findById(id).orElse(null);
        if(find != null){
            find.setName(category.getName());
            return repo.save(find);
        }else return null;
    }

    @Override
    public boolean deleteCategory(Integer id) {
        if(repo.findById(id).orElse(null) != null) {
            repo.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
