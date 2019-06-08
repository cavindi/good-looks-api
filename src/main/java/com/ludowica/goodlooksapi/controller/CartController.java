package com.ludowica.goodlooksapi.controller;

import com.ludowica.goodlooksapi.form.CartForm;
import com.ludowica.goodlooksapi.model.Cart;
import com.ludowica.goodlooksapi.repository.CartRepo;
import com.ludowica.goodlooksapi.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/carts")
public class CartController {

    @Autowired
    CartRepo cartRepo;

    @Autowired
    CartService cartService;

    @GetMapping
    public List<Cart> getAll() {
        return cartRepo.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Cart> getCart(@PathVariable int id) {
        return cartRepo.findById(id);
    }

    @GetMapping("/fetch/{userId}")
    public ResponseEntity<?> fetchCart(@PathVariable int userId) {

        Cart shoppingCart = cartService.retrieveCart(userId);
        return new ResponseEntity<>(shoppingCart, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> manageProduct(@RequestBody CartForm cartForm) {
        return cartService.manageProduct(cartForm);
    }

    @DeleteMapping("/{id}")
    public void removeCart(@PathVariable int id) {
        cartRepo.deleteById(id);
    }

    @DeleteMapping
    public ResponseEntity<?> removeCartProduct(@RequestBody CartForm cartForm) {
        return cartService.deleteCartProduct(cartForm);
    }
}
