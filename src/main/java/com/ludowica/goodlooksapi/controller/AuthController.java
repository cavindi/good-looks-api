package com.ludowica.goodlooksapi.controller;

import com.ludowica.goodlooksapi.model.User;
import com.ludowica.goodlooksapi.model.UserSignUp;
import com.ludowica.goodlooksapi.repository.UserRepo;
import com.ludowica.goodlooksapi.security.JwtProvider;
import com.ludowica.goodlooksapi.security.JwtResponse;
import com.ludowica.goodlooksapi.security.ResponseMessage;
import com.ludowica.goodlooksapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepo userRepo;

    @Autowired
    UserService userService;

    @Autowired
    JwtProvider jwtProvider;


    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody User user) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        JwtResponse jwtResponse = new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        jwtResponse.setUserId(userRepo.findByUsername(userDetails.getUsername()).get().getId());

        return ResponseEntity.ok(jwtResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserSignUp userSignUp) {

        if (userRepo.existsByUsername(userSignUp.getUsername())) {
            return new ResponseEntity<>(new ResponseMessage("Username already exists"), HttpStatus.BAD_REQUEST);
        }

        if (userRepo.existsByEmail(userSignUp.getEmail())) {
            return new ResponseEntity<>(new ResponseMessage("Email already exists"), HttpStatus.BAD_REQUEST);
        }

        userService.createUser(userSignUp);

        return new ResponseEntity<>(new ResponseMessage("User registered successfully"), HttpStatus.OK);
    }

}
