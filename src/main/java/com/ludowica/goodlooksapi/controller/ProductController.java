package com.ludowica.goodlooksapi.controller;

import com.ludowica.goodlooksapi.model.Product;
import com.ludowica.goodlooksapi.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductRepo productRepo;

    @GetMapping
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Product> getProduct(@PathVariable int id) {
        return productRepo.findById(id);
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productRepo.save(product);
    }
    
    @PutMapping
    public Product updateProduct(@RequestBody Product product) {
        return productRepo.save(product);
    }

    @DeleteMapping("/{id}")
    public boolean deleteProduct(@PathVariable int id) {
        if (productRepo.existsById(id)) {
            productRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
