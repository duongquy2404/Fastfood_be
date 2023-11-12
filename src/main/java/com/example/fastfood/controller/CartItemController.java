package com.example.fastfood.controller;

import com.example.fastfood.model.entity.CartItem;
import com.example.fastfood.service.CartItemService;
import com.example.fastfood.service.CartService;
import com.example.fastfood.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/cart_item")
public class CartItemController {
    @Autowired
    private CartItemService cartItemService;
    @Autowired
    private CartService cartService;

    @PostMapping("/{cartId}/cartitems")
    public CartItem addToCart(@PathVariable Long cartId, @RequestBody CartItem cartItem) {
        return cartItemService.addCartItem(cartId, cartItem);
    }
}
