package com.example.fastfood.controller;

import com.example.fastfood.model.entity.Cart;
import com.example.fastfood.model.entity.CartItem;
import com.example.fastfood.model.entity.Food;
import com.example.fastfood.service.CartItemService;
import com.example.fastfood.service.CartService;
import com.example.fastfood.service.FoodService;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/cart_item")
public class CartItemController {
    @Autowired
    private CartItemService cartItemService;
    @Autowired
    private CartService cartService;
    @Autowired
    private FoodService foodService;

    @PostMapping("/add")
    public ResponseEntity<CartItem> addToCart(@RequestBody CartItem cartItem) {
        Optional<Cart> optionalCart = cartService.getCartById(cartItem.getCart().getId());
        if (optionalCart.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Optional<Food> optionalFood = foodService.findFoodById(cartItem.getFood().getId());
        if (optionalFood.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Cart cart = optionalCart.get();
        Food food = optionalFood.get();
        Hibernate.initialize(cart);
        Hibernate.initialize(food);

        CartItem newCartItem = new CartItem();
        newCartItem.setCart(cart);
        newCartItem.setFood(food);
        newCartItem.setQuantity(cartItem.getQuantity());

        CartItem savedCartItem = cartItemService.addCartItem(newCartItem);

        return new ResponseEntity<>(savedCartItem, HttpStatus.CREATED);
    }
}
