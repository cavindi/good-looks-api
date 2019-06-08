package com.ludowica.goodlooksapi.controller;

import com.ludowica.goodlooksapi.model.Cart;
import com.ludowica.goodlooksapi.model.Product;
import com.ludowica.goodlooksapi.repository.CartRepo;
import com.ludowica.goodlooksapi.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/checkout")
public class CheckoutController {

    @Autowired
    CartRepo cartRepo;

    @Autowired
    CartService cartService;

    @GetMapping("/{id}")
    public ResponseEntity<?> customerCheckout(@PathVariable int id) {
        Cart shoppingCart = doCheckOut(id);
        return new ResponseEntity<>(shoppingCart, HttpStatus.OK);
    }


    public Cart doCheckOut(int id) {

        Cart shoppingCart = updateCartStatus(id);
        int userId = shoppingCart.getUserId();

        Cart newShoppingCart = cartService.retrieveCart(userId);

        return newShoppingCart;
    }

    public Cart updateCartStatus(int id) {

        Cart shoppingCart = cartRepo
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found for this id :: " + id));

        shoppingCart.setStatus("checkout");
        shoppingCart = cartRepo.save(shoppingCart);

        return shoppingCart;
    }

}