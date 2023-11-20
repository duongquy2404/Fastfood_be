package com.example.fastfood.controller;

import com.example.fastfood.model.entity.Food;
import com.example.fastfood.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/food")
public class FoodController {
    @Autowired
    private FoodService foodService;

    @GetMapping("/all")
    public ResponseEntity<List<Food>> getAllFood() {
        List<Food> foodList = foodService.getAllFood();
        if (foodList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(foodList, HttpStatus.OK);
    }

    @GetMapping("/all/{categoryId}")
    public ResponseEntity<List<Food>> getFoodsByCategory(@PathVariable Long categoryId) {
        List<Food> foods = foodService.getAllFoodByCategory(categoryId);
        if (foods.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(foods, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Food> getFoodById(@PathVariable Long id) {
        Optional<Food> food = foodService.findFoodById(id);
        return food.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Food>> searchFoodsByName(@RequestParam String name) {
        List<Food> foods = foodService.findFoodsByName(name);
        if (foods.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(foods, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Food> addFood(@RequestBody Food food) {
        Food newFood = foodService.addNewFood(food);
        return new ResponseEntity<>(newFood, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Food> updateFood(@PathVariable Long id, @RequestBody Food food) {
        if (!id.equals(food.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        foodService.updateFood(food);
        return foodService != null ?
                new ResponseEntity<>(food, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Food> deleteFood(@PathVariable Long id){
        if (foodService.findFoodById(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        foodService.deleteFood(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
