package com.example.backend.api.response;

import com.example.backend.database.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoggedInRS {
    private String status;
    private UserEntity userEntity;
}
