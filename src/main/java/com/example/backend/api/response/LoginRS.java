package com.example.backend.api.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRS {
    private String status;
    private String jwt;
}
