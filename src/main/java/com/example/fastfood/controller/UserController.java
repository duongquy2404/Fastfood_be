package com.example.fastfood.controller;

import com.example.fastfood.model.dto.UserLoginDTO;
import com.example.fastfood.model.dto.UserRegisterDTO;
import com.example.fastfood.model.entity.Cart;
import com.example.fastfood.model.entity.User;
import com.example.fastfood.service.CartService;
import com.example.fastfood.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDTO userLoginDTO) {
        HashMap<String, Object> response = new HashMap<>();
        HashMap<String, Object> error = new HashMap<>();

        String username = userLoginDTO.getUsername();
        String password = userLoginDTO.getPassword();
        try {
            User user = userService.login(username);
            if (user != null) {
                if (bCryptPasswordEncoder.matches(password, user.getPassword())) {
                    response.put("message", "User logged in successfully");
                    response.put("user", user);
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

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegisterDTO userRegisterDTO){
        HashMap<String, Object> response = new HashMap<>();
        HashMap<String, Object> error = new HashMap<>();

        if(!userService.checkUserByUsername(userRegisterDTO.getUsername())){
            error.put("message", "Registered Username");
            return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
        }else{
            try{
                String encodedPassword = bCryptPasswordEncoder.encode(userRegisterDTO.getPassword());

                User user = new User();
                user.setName(userRegisterDTO.getName());
                user.setUsername(userRegisterDTO.getUsername());
                user.setPassword(encodedPassword);
                userService.register(user);

                Cart cart=new Cart();
                cart.setTotalPrice((double) 0);
                cart.setUser(user);
                cartService.createCart(cart);

                response.put("message", "User registered successfully");
                response.put("user", user);
                return new ResponseEntity<>(response,HttpStatus.OK);
            }catch (RuntimeException e){
                error.put("message", e.getMessage());
                return  new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
            }
        }
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable Long userId, @RequestBody User updatedUser) {
        Optional<User> optionalUser = userService.getUserById(userId);

        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();

            // Cập nhật thông tin người dùng
            existingUser.setName(updatedUser.getName());
            existingUser.setPhone(updatedUser.getPhone());
            existingUser.setAddress(updatedUser.getAddress());
            // Cập nhật các trường thông tin khác

            userService.updateUser(existingUser);

            return new ResponseEntity<>("Thông tin người dùng đã được cập nhật", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Người dùng không tồn tại", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/avatar/{userId}")
    public ResponseEntity<String> updateAvatar(@PathVariable Long userId, @RequestBody User updatedUser) {
        Optional<User> optionalUser = userService.getUserById(userId);

        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();

            existingUser.setAvatar(updatedUser.getAvatar());

            userService.updateUser(existingUser);

            return new ResponseEntity<>("Avatar người dùng đã được cập nhật", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Người dùng không tồn tại", HttpStatus.NOT_FOUND);
        }
    }
}
