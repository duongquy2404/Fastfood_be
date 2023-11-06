package com.example.fastfood.service.impl;

import com.example.fastfood.model.dto.UserRegisterDTO;
import com.example.fastfood.model.entity.User;
import com.example.fastfood.repository.UserRepositoty;
import com.example.fastfood.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public User registerUser(UserRegisterDTO userDTO) {
        return null;
    }

    @Override
    public User login(String username) {
        return null;
    }

    @Override
    public boolean checkUserByUsername(String username) {
        return false;
    }

    @Override
    public Optional<User> getUserById(Integer id) {
        return Optional.empty();
    }

    @Override
    public User saveUser(User user) {
        return null;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<User> findAllUsers() {
        return null;
    }

    @Override
    public User updateUser(Long id, User user) {
        return null;
    }

    @Override
    public void deleteUser(Long id) {

    }

    @Override
    public List<User> findUsersByCriteria(String criteria) {
        return null;
    }

    @Override
    public void changePassword(Long userId, String newPassword) {

    }

    @Override
    public User login(String username, String password) {
        return null;
    }

    @Override
    public boolean checkIfUserHasRole(Long userId, String role) {
        return false;
    }
}
