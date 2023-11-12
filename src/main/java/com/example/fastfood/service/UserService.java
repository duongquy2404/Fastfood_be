package com.example.fastfood.service;

import com.example.fastfood.model.entity.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    void register(User user);

    User login(String username);

    boolean checkUserByUsername(String username);

    Optional<User> getUserById(Long id);
}
