package com.example.fastfood.service.impl;

import com.example.fastfood.model.entity.Cart;
import com.example.fastfood.model.entity.CartItem;
import com.example.fastfood.model.entity.Food;
import com.example.fastfood.repository.CartItemRepository;
import com.example.fastfood.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemServiceImpl implements CartItemService {
    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public List<CartItem> getCartItemByCartId(Long cartId) {
        return cartItemRepository.findByCartId(cartId);
    }

    @Override
    public Optional<CartItem> findCartItemById(Long id) {
        return cartItemRepository.findById(id);
    }

    @Override
    public void createCartItem(CartItem cartItem) {
        Long foodId = cartItem.getFood().getId();
        Long cartId = cartItem.getCart().getId();
        int quantity = cartItem.getQuantity();

        // Kiểm tra xem CartItem đã tồn tại hay chưa
        Optional<CartItem> existingCartItem = cartItemRepository.findByFoodIdAndCartId(foodId, cartId);

        if (existingCartItem.isPresent()) {
            // Nếu đã tồn tại, tăng số lượng lên cartItem.getQuantity()
            CartItem cartItemTmp = existingCartItem.get();
            cartItemTmp.setQuantity(cartItem.getQuantity() + cartItemTmp.getQuantity());
            cartItemRepository.save(cartItemTmp);
        } else {
            // Nếu chưa tồn tại, tạo mới CartItem
            CartItem newCartItem = new CartItem();
            Food food=new Food();
            food.setId(foodId);
            Cart cart=new Cart();
            cart.setId(cartId);
            newCartItem.setFood(food);
            newCartItem.setCart(cart);
            newCartItem.setQuantity(quantity);
            // Các thông tin khác nếu có

            cartItemRepository.save(newCartItem);
        }
    }

    @Override
    public void updateCartItem(CartItem cartItem) {
        cartItemRepository.save(cartItem);
    }

    @Override
    public void deleteCartItem(Long id) {
        cartItemRepository.deleteById(id);
    }
}
