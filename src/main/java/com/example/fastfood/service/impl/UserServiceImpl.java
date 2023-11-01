package com.example.fastfood.service.impl;

import com.example.fastfood.model.dto.UserRegisterDTO;
import com.example.fastfood.model.entity.User;
import com.example.fastfood.repository.UserRepositoty;
import com.example.fastfood.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UserServiceImpl implements UserService {
    @Autowired
    UserRepositoty repositoty;
    @Override
    public User registerUser(UserRegisterDTO userDTO) {
        User user=new User();
        user.setName(userDTO.getName());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        return repositoty.save(user);
    }

    @Override
    public User login(String username) {
        return repositoty.findUserByUsername(username);
    }

    @Override
    public boolean checkUserByUsername(String username) {
        User user=repositoty.findUserByUsername(username);
        if(user==null)return true;
        return false;
    }

    @Override
    public Optional<User> getUserById(Integer id) {
        return repositoty.findById(id);
    }
}
