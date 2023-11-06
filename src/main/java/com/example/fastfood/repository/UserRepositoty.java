package com.example.fastfood.repository;

import com.example.fastfood.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoty  extends JpaRepository<User, Long> {
    User findUserByUsername(String username);
}
