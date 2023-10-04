package com.example.backend.database.dto;

public interface UserDTO {
    String getName();
    void setName(String name);
    String getPublicKey();
    void setPublicKey(String publicKey);
    String getPassword();
    void setPassword(String password);
}
