package com.example.fastfood.controller;

import com.example.fastfood.model.entity.CartItem;
import com.example.fastfood.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/{cartId}/cartitems")
    public List<CartItem> getCartItemsByUserId(@PathVariable Long cartId) {
        return cartService.getCartItemsByCartId(cartId);
    }
}
