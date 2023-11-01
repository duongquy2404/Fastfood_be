package com.example.fastfood.controller;

import com.example.fastfood.model.dto.UserLoginDTO;
import com.example.fastfood.model.dto.UserRegisterDTO;
import com.example.fastfood.model.entity.User;
import com.example.fastfood.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@CrossOrigin
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDTO userLoginDTO) {
        HashMap<String, Object> response = new HashMap<>();
        HashMap<String, Object> error = new HashMap<>();

        String passwordRemote = userLoginDTO.getPassword();
        String usernameRemote = userLoginDTO.getUsername();
        try {
            User userlocal = userService.login(usernameRemote);
            if (userlocal != null) {
                if (userlocal.getPassword().equals(passwordRemote)) {
                    response.put("message", "User logged in successfully");
                    response.put("user", userlocal);
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

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegisterDTO userRegisterDTO){
        HashMap<String, Object> response = new HashMap<>();
        HashMap<String, Object> error = new HashMap<>();

        if(!userService.checkUserByUsername(userRegisterDTO.getUsername())){
            error.put("message", "registered username");
            return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
        }else{
            try{
                User userRegister = userService.registerUser(userRegisterDTO);
                response.put("message", "User registered successfully");
                response.put("user", userRegister);
                return new ResponseEntity<>(response,HttpStatus.OK);
            }catch (RuntimeException e){
                error.put("message", e.getMessage());
                return  new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
            }
        }
    }
}
