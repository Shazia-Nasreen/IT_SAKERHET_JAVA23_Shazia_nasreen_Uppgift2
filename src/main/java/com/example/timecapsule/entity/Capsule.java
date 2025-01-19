package com.example.timecapsule.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "capsules")
public class Capsule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String encryptedMessage;

    public Capsule() {}

    public Capsule(Long userId, String encryptedMessage) {
        this.userId = userId;
        this.encryptedMessage = encryptedMessage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEncryptedMessage() {
        return encryptedMessage;
    }

    public void setEncryptedMessage(String encryptedMessage) {
        this.encryptedMessage = encryptedMessage;
    }
}
