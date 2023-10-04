package com.example.backend.database.entity;

import com.example.backend.database.dto.UserDTO;
import com.sun.istack.NotNull;
import lombok.ToString;

import javax.persistence.*;

@Entity
@ToString
@Table(name = "user")
public class UserEntity implements UserDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(name = "name")
    private String name;
    @NotNull
    @Column(name = "public_key")
    private String publicKey;
    @NotNull
    @Column(name = "password")
    private String password;

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name=name;
    }

    @Override
    public String getPublicKey() {
        return this.publicKey;
    }

    @Override
    public void setPublicKey(String publicKey) {
        this.publicKey=publicKey;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public void setPassword(String password) {
        this.password=password;
    }
}
