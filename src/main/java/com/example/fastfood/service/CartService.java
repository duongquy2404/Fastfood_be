package com.example.fastfood.service;

import com.example.fastfood.model.entity.Cart;
import com.example.fastfood.model.entity.CartItem;
import java.util.List;
import java.util.Optional;

public interface CartService {
    Cart getCartIdByUserId(Long userId);

    Cart createCart(Cart cart);

    List<CartItem> getCartItemsByCartId(Long id);

    Optional<Cart> getCartById(Long id);
}
