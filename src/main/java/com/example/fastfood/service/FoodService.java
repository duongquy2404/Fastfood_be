package com.example.fastfood.service;

import com.example.fastfood.model.entity.Food;
import java.util.List;
import java.util.Optional;

public interface FoodService {
    List<Food> getAllFood();
    List<Food> getAllFoodByCategory(Long categoryId);
    List<Food> findFoodsByName(String nameFood);
    Optional<Food> findFoodById(Long id);
    Food addNewFood(Food food);
    Food updateFood(Food food);
    void deleteFood(Long id);
}
