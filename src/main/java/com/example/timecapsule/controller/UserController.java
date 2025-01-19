package com.example.timecapsule.controller;

import com.example.timecapsule.entity.User;
import com.example.timecapsule.service.UserService;
import com.example.timecapsule.service.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        try {
            userService.register(user.getEmail(), user.getPassword());
            return ResponseEntity.ok("User registered successfully");
        } catch (NoSuchAlgorithmException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error registering user");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        try {
            User loggedInUser = userService.login(user.getEmail(), user.getPassword());
            if (loggedInUser != null) {
                String token = jwtUtil.generateToken(loggedInUser.getEmail());
                return ResponseEntity.ok(token);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error logging in user: " + e.getMessage());
        }
    }

}
