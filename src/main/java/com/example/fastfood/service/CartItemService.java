package com.example.fastfood.service;

import com.example.fastfood.model.entity.CartItem;

public interface CartItemService {
    CartItem addCartItem(CartItem cartItem);

    CartItem updateCartItem(CartItem cartItem);

    void deleteCartItem(Long id);
}
