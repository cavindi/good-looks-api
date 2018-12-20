package com.ludowica.goodlooksapi.controller;

import com.ludowica.goodlooksapi.model.User;
import com.ludowica.goodlooksapi.repository.AuthRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthRepo authRepo;

    @PostMapping
    public User login(@RequestBody User user) {
        return authRepo.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

}
