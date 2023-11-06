package com.example.fastfood.repository;

import com.example.fastfood.model.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByCartId(Long cartId);

    CartItem findByCartIdAndProductId(Long cartId, Long productId);
}
