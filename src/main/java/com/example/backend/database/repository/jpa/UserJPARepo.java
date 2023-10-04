package com.example.backend.database.repository.jpa;

import com.example.backend.database.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJPARepo extends JpaRepository<UserEntity,Long> {
    UserEntity findByPublicKey(String publicKey);
}
