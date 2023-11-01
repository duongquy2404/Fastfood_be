package com.example.fastfood.service;

import com.example.fastfood.model.dto.UserRegisterDTO;
import com.example.fastfood.model.entity.User;

import java.util.Optional;

public interface UserService {
    User registerUser(UserRegisterDTO userDTO);
    User login(String username);

    boolean checkUserByUsername(String username);

    Optional<User> getUserById(Integer id);
}
