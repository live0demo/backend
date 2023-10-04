package com.example.backend.api.request;

import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@ApiModel(value = "RegisterRQ", description = "Đăng ký tài khoản mới")
@Getter
@Setter
public class RegisterRQ {
    @ApiModelProperty(value = "name")
    @NotBlank
    private String name;

    @ApiModelProperty(value = "public key")
    @NotBlank
    private String publicKey;

    @ApiModelProperty(value = "password")
    @NotBlank
    private String password;
}
