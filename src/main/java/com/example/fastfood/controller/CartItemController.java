package com.example.fastfood.controller;

import com.example.fastfood.model.entity.CartItem;
import com.example.fastfood.service.CartItemService;
import com.example.fastfood.service.CartService;
import com.example.fastfood.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{cartId}")
    public ResponseEntity<List<CartItem>> getCartItemsByCartId(@PathVariable Long cartId) {
        try {
            List<CartItem> cartItems = cartItemService.getCartItemByCartId(cartId);
            return new ResponseEntity<>(cartItems, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<CartItem> createCartItem(@RequestBody CartItem cartItem) {
        try {
            cartItemService.createCartItem(cartItem);
            return new ResponseEntity<>(cartItem, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(cartItem, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CartItem> updateCartItem(@PathVariable Long id, @RequestBody CartItem cartItem){
        if (!id.equals(cartItem.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        cartItemService.updateCartItem(cartItem);
        return cartItemService != null ?
                new ResponseEntity<>(cartItem, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CartItem> deleteCartItem(@PathVariable Long id){
        if (cartItemService.findCartItemById(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        cartItemService.deleteCartItem(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
