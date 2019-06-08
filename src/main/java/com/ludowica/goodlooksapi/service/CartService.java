package com.ludowica.goodlooksapi.service;

import com.ludowica.goodlooksapi.form.CartForm;
import com.ludowica.goodlooksapi.model.Cart;
import com.ludowica.goodlooksapi.model.CartProduct;
import com.ludowica.goodlooksapi.model.Product;
import com.ludowica.goodlooksapi.repository.CartProductRepo;
import com.ludowica.goodlooksapi.repository.CartRepo;
import com.ludowica.goodlooksapi.repository.ProductRepo;
import com.ludowica.goodlooksapi.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CartService {

    @Autowired
    ProductRepo productRepo;

    @Autowired
    CartRepo cartRepo;

    @Autowired
    CartProductRepo cartProductRepo;

    @Autowired
    UserRepo userRepo;


    private CartProduct cartProduct = null;

    public ResponseEntity<?> manageProduct(CartForm cartForm) {

        Product product = productRepo
                .findById(cartForm.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + cartForm.getProductId()));

        Cart cart = cartRepo
                .findById(cartForm.getCartId())
                .orElseThrow(() -> new ResourceNotFoundException("Shopping Cart not found for this id :: " + cartForm.getCartId()));


        Optional<CartProduct> cartProductOptional = cartProductRepo
                .findByProductIdAndAndCartId(cartForm.getProductId(), cartForm.getCartId());

        if (cartProductOptional.isPresent()) {
            update(cartForm, cartProductOptional);
        } else {
            add(cartForm, cart, product);
        }


        return new ResponseEntity<>(this.cartProduct, HttpStatus.OK);
    }

    private void update(CartForm cartForm, Optional<CartProduct> cartProductOptional) {
        cartProduct = cartProductOptional.get();
        cartProduct.setQuantity(cartForm.getQuantity());

        cartProductRepo.save(cartProduct);
    }

    private void add(CartForm cartForm, Cart cart, Product product) {

        cartProduct = new CartProduct();
        cartProduct.setProduct(product);
        cartProduct.setQuantity(cartForm.getQuantity());
        cartProduct.setCart(cart);

        cartProductRepo.save(cartProduct);
    }

    public ResponseEntity<?> deleteCartProduct(CartForm cartForm) {

        this.cartProduct = cartProductRepo
                .findByProductIdAndAndCartId(cartForm.getProductId(), cartForm.getCartId())
                .orElseThrow(() -> new ResourceNotFoundException("Cart Product not found for this id :: " + cartForm.getCartId()));

        cartProductRepo.deleteById(this.cartProduct.getId());

        return new ResponseEntity<>(this.cartProduct, HttpStatus.OK);
    }

    public Cart retrieveCart(int userId) {

        Optional<Cart> shoppingCartOptional = cartRepo.findByUserIdAndStatus(userId, "ongoing");
        Cart shoppingCart = null;

        if (shoppingCartOptional.isPresent()) {
            shoppingCart = shoppingCartOptional.get();
        } else {

            userRepo
                    .findById(userId)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));

            shoppingCart = new Cart();
            shoppingCart.setStatus("ongoing");
            shoppingCart.setUserId(userId);
            cartRepo.save(shoppingCart);
        }

        return shoppingCart;
    }
}
