package com.ludowica.goodlooksapi.repository;

import com.ludowica.goodlooksapi.model.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartProductRepo extends JpaRepository<CartProduct, Integer> {
    Optional<CartProduct> findByProductIdAndAndCartId(int productId, int cartId);
}
