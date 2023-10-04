package com.example.backend.service;

import com.example.backend.api.request.LoginRQ;
import com.example.backend.api.request.RegisterRQ;
import com.example.backend.api.response.LoggedInRS;
import com.example.backend.api.response.LoginRS;
import com.example.backend.api.response.RegisterRS;
import com.example.backend.database.entity.UserEntity;

public interface UserService {
    RegisterRS registerAccount(RegisterRQ registerRQ);
    LoginRS login(LoginRQ loginRQ);
    LoggedInRS getUserLoggedIn();
}
