package com.example.backend.api.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LoginRQ {
    @ApiModelProperty(value = "public key")
    @NotBlank
    private String publicKey;

    @ApiModelProperty(value = "password")
    @NotBlank
    private String password;
}
