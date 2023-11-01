package com.example.fastfood.controller;

import com.example.fastfood.model.dto.AdminLoginDTO;
import com.example.fastfood.model.entity.Admin;
import com.example.fastfood.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@CrossOrigin
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AdminLoginDTO adminLoginDTO) {
        HashMap<String, Object> response = new HashMap<>();
        HashMap<String, Object> error = new HashMap<>();

        String passwordRemote = adminLoginDTO.getPassword();
        String usernameRemote = adminLoginDTO.getUsername();
        try {
            Admin adminlocal = adminService.login(usernameRemote);
            if (adminlocal != null) {
                if (adminlocal.getPassword().equals(passwordRemote)) {
                    response.put("message", "User logged in successfully");
                    response.put("user", adminlocal);
                    return new ResponseEntity<>(response, HttpStatus.OK);
                } else {
                    error.put("message", "Password is incorrect");
                    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
                }
            } else {
                error.put("message", "Phone is incorrect");
                return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
            }
        } catch (RuntimeException e) {
            error.put("message", e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
    }
}
