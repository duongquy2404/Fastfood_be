package com.example.fastfood.service.impl;

import com.example.fastfood.model.entity.Cart;
import com.example.fastfood.model.entity.CartItem;
import com.example.fastfood.repository.CartRepository;
import com.example.fastfood.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;

    @Override
    public Cart getCartIdByUserId(Long userId) {
        return cartRepository.findByUserId(userId);
    }

    @Override
    public Cart createCart(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public List<CartItem> getCartItemsByCartId(Long id) {
        Cart cart = cartRepository.findByUserId(id);
        if (cart != null) {
            return cart.getCartItems();
        }
        return Collections.emptyList();
    }

    @Override
    public Optional<Cart> getCartById(Long id) {
        return cartRepository.findById(id);
    }
}
