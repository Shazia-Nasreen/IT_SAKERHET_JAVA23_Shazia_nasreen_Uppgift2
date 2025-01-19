package com.example.timecapsule.service;

import com.example.timecapsule.entity.User;
import com.example.timecapsule.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void register(String email, String password) throws NoSuchAlgorithmException {
        String hashedPassword = hashPassword(password);
        User user = new User();
        user.setEmail(email);
        user.setPassword(hashedPassword);
        userRepository.save(user);
    }

    public User login(String email, String password) throws NoSuchAlgorithmException {
        String hashedPassword = hashPassword(password);
        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(hashedPassword)) {
            return user;
        }
        return null;
    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashedBytes = md.digest(password.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : hashedBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
