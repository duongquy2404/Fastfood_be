package com.example.fastfood.controller;

import com.example.fastfood.model.entity.Category;
import com.example.fastfood.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/all")
    public ResponseEntity<Object> getAllCategory() {
        HashMap<String, Object> response = new HashMap<>();
        HashMap<String, Object> error = new HashMap<>();

        List<Category> categoryList = categoryService.getAllCategory();
        if (categoryList.isEmpty()) {
            error.put("message", "Category not found");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        } else {
            response.put("message", "Get all is successfully");
            response.put("category", categoryList);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
}
