package com.example.fastfood.repository;

import com.example.fastfood.model.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByCartId(Long cartId);

    CartItem findByCartIdAndFoodId(Long cartId, Long foodId);

    Optional<CartItem> findByFoodIdAndCartId(Long foodId, Long cartId);
}
