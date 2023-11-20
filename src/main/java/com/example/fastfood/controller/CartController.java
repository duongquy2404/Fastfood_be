package com.example.fastfood.controller;

import com.example.fastfood.model.entity.Cart;
import com.example.fastfood.model.entity.CartItem;
import com.example.fastfood.model.entity.Food;
import com.example.fastfood.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/{userId}")
    public Cart getCartByUser(@PathVariable Long userId){
        return cartService.getCartByUserId(userId);
    }

    @GetMapping("/{cartId}/cartitems")
    public List<CartItem> getCartItemsByUserId(@PathVariable Long cartId) {
        return cartService.getCartItemsByCartId(cartId);
    }
}
