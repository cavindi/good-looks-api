package com.ludowica.goodlooksapi.controller;

import com.ludowica.goodlooksapi.model.Category;
import com.ludowica.goodlooksapi.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    CategoryRepo categoryRepo;

    @GetMapping("{id}")
    public Optional<Category> getCategoryProduct(@PathVariable int id) {
        return categoryRepo.findById(id);
    }

    @GetMapping
    public List<Category> getAllCategory() {
        return categoryRepo.findAll();
    }

    @PostMapping
    public Category addCategoryProduct(@RequestBody Category category) {
        return categoryRepo.save(category);
    }
}
