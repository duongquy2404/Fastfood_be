package com.example.fastfood.service.impl;

import com.example.fastfood.model.entity.User;
import com.example.fastfood.repository.UserRepositoty;
import com.example.fastfood.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    public UserRepositoty userRepository;

    @Override
    public void register(User user) {
        userRepository.save(user);
    }

    @Override
    public User login(String phone) {
        return userRepository.findUserByUsername(phone);
    }

    @Override
    public boolean checkUserByUsername(String username) {
        User user = userRepository.findUserByUsername(username);
        if (user == null) return true;
        return false;
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
}
