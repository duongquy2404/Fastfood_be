package com.example.fastfood.service.impl;

import com.example.fastfood.model.entity.Food;
import com.example.fastfood.repository.FoodRepository;
import com.example.fastfood.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class FoodServiceImpl implements FoodService {
    @Autowired
    private FoodRepository foodRepository;

    @Override
    public List<Food> getAllFood() {
        return foodRepository.findAll();
    }

    @Override
    public List<Food> getAllFoodByCategory(Long categoryId) {
        return foodRepository.findByCategoryId(categoryId);
    }

    @Override
    public List<Food> findFoodsByName(String nameFood) {
        return foodRepository.findByNameContaining(nameFood);
    }

    @Override
    public Optional<Food> findFoodById(Long id) {
        return foodRepository.findById(id);
    }

    @Override
    public Food addNewFood(Food food) {
        return foodRepository.save(food);
    }

    @Override
    public Food updateFood(Food food) {
        return foodRepository.save(food);
    }

    @Override
    public void deleteFood(Long id) {
        foodRepository.deleteById(id);
    }
}
