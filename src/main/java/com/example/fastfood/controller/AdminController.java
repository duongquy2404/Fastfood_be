package com.example.fastfood.controller;

import com.example.fastfood.model.dto.AdminLoginDTO;
import com.example.fastfood.model.dto.UserLoginDTO;
import com.example.fastfood.model.entity.Admin;
import com.example.fastfood.model.entity.User;
import com.example.fastfood.service.AdminService;
import com.example.fastfood.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AdminLoginDTO adminLoginDTO) {
        HashMap<String, Object> response = new HashMap<>();
        HashMap<String, Object> error = new HashMap<>();

        String username = adminLoginDTO.getUsername();
        String password = adminLoginDTO.getPassword();
        try {
            Admin admin = adminService.login(username);
            if (admin != null) {
                if (password.matches(admin.getPassword())) {
                    response.put("message", "Admin logged in successfully");
                    response.put("admin", admin);
                    return new ResponseEntity<>(response, HttpStatus.OK);
                } else {
                    error.put("message", "Password is incorrect");
                    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
                }
            } else {
                error.put("message", "Username is incorrect");
                return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
            }
        } catch (RuntimeException e) {
            error.put("message", e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
    }
}
