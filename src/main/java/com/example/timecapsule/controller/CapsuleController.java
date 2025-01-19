package com.example.timecapsule.controller;

import com.example.timecapsule.entity.Capsule;
import com.example.timecapsule.repository.CapsuleRepository;
import com.example.timecapsule.service.EncryptionService;
import com.example.timecapsule.service.JwtUtil;
import com.example.timecapsule.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/capsules")
public class CapsuleController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private EncryptionService encryptionService;

    @Autowired
    private CapsuleRepository capsuleRepository;

    @PostMapping("/create")
    public ResponseEntity<String> createCapsule(@RequestBody String message, @RequestHeader("Authorization") String token) {
        try {
            String email = jwtUtil.extractEmail(token);
            var user = userService.findUserByEmail(email);

            String encryptedMessage = encryptionService.encrypt(message);
            Capsule capsule = new Capsule(user.getId(), encryptedMessage);

            System.out.println("Saving capsule: " + capsule);

            capsuleRepository.save(capsule);
            return ResponseEntity.ok("Capsule created successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating capsule");
        }
    }


    @GetMapping("/read")
    public ResponseEntity<List<String>> readCapsules(@RequestHeader("Authorization") String token) {
        try {
            String email = jwtUtil.extractEmail(token);
            var user = userService.findUserByEmail(email);

            List<Capsule> capsules = capsuleRepository.findByUserId(user.getId());

            List<String> decryptedMessages = capsules.stream()
                    .map(capsule -> {
                        try {
                            return encryptionService.decrypt(capsule.getEncryptedMessage());
                        } catch (Exception e) {
                            return "Error decrypting message";
                        }
                    })
                    .collect(Collectors.toList());

            return ResponseEntity.ok(decryptedMessages);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(List.of("Error retrieving capsules"));
        }
    }
}
