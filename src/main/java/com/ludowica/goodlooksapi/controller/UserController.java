package com.ludowica.goodlooksapi.controller;

import com.ludowica.goodlooksapi.model.User;
import com.ludowica.goodlooksapi.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserRepo userRepo;

    @GetMapping
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @GetMapping("/{id}")
    public Optional<User> getUser(@PathVariable int id) {
        return userRepo.findById(id);
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        return userRepo.save(user);
    }

    @PutMapping
    public User updateUser(@RequestBody User user) {
        return userRepo.save(user);
    }

    @DeleteMapping
    public boolean removeUser(@PathVariable int id) {
        if (userRepo.existsById(id)) {
            userRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
