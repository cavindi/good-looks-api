package com.ludowica.goodlooksapi.service;

import com.ludowica.goodlooksapi.model.Product;
import com.ludowica.goodlooksapi.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepo productRepo;

    public void addProduct(int id){
       Optional<Product> product =  productRepo.findById(id);
    }



}
