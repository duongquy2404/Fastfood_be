package com.example.fastfood.repository;

import com.example.fastfood.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositoty  extends JpaRepository<User, Integer> {
    User findUserByUsername(String username);
}
