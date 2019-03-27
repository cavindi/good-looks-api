package com.ludowica.goodlooksapi.repository;

import com.ludowica.goodlooksapi.model.Cart;
import com.ludowica.goodlooksapi.model.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepo extends JpaRepository<Cart, Integer> {
}
