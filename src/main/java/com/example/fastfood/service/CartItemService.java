package com.example.fastfood.service;

import com.example.fastfood.model.entity.CartItem;

import java.util.List;
import java.util.Optional;

public interface CartItemService {
    List<CartItem> getCartItemByCartId(Long cartId);
    Optional<CartItem> findCartItemById(Long id);
    void createCartItem(CartItem cartItem);

    void updateCartItem(CartItem cartItem);

    void deleteCartItem(Long id);
}
