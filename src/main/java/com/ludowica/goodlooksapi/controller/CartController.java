package com.ludowica.goodlooksapi.controller;

import com.ludowica.goodlooksapi.model.Cart;
import com.ludowica.goodlooksapi.repository.CartRepo;
import com.ludowica.goodlooksapi.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    CartRepo cartRepo;

    @Autowired
    CartService cartService;


    @PostMapping
    public Cart addProduct(@RequestBody int productId) {
        return cartService.addToCart(productId);
    }


}
