package com.ludowica.goodlooksapi.repository;

import com.ludowica.goodlooksapi.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepo extends JpaRepository<Cart, Integer> {
}
