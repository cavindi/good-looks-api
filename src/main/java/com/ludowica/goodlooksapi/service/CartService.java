package com.ludowica.goodlooksapi.service;

import com.ludowica.goodlooksapi.exception.ResourceException;
import com.ludowica.goodlooksapi.model.Cart;
import com.ludowica.goodlooksapi.model.Product;
import com.ludowica.goodlooksapi.repository.CartRepo;
import com.ludowica.goodlooksapi.repository.CategoryRepo;
import com.ludowica.goodlooksapi.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {

    @Autowired
    ProductRepo productRepo;

    @Autowired
    CartRepo cartRepo;

    public Cart addToCart(int id){


        Product product =  productRepo.findById(id).orElseThrow(() -> new ResourceException("not found"));

        Cart c = new Cart();
        c.setProductId(product.getId());
        c.setProductName(product.getName());

        return cartRepo.save(c);
    }
}
