package com.example.fastfood.repository;

import com.example.fastfood.model.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
    List<Food> findByCategoryId(Long categoryId);

    List<Food> findByNameContaining(String name);
}
