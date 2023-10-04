package com.example.backend.service.impl;

import com.example.backend.api.request.LoginRQ;
import com.example.backend.api.request.RegisterRQ;
import com.example.backend.api.response.LoggedInRS;
import com.example.backend.api.response.LoginRS;
import com.example.backend.api.response.RegisterRS;
import com.example.backend.database.entity.UserEntity;
import com.example.backend.database.repository.repo.UserRepo;
import com.example.backend.service.JwtService;
import com.example.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JwtService jwtService;

    @Override
    public RegisterRS registerAccount(RegisterRQ registerRQ) {
        UserEntity existingUser=userRepo.findByPublicKey(registerRQ.getPublicKey());
        RegisterRS registerRS=new RegisterRS();
        if(existingUser!=null){
            registerRS.setStatus("Bad request. User with provided public key already exists.");
            return registerRS;
        }
        UserEntity userEntity=new UserEntity();
        userEntity.setName(registerRQ.getName());
        userEntity.setPublicKey(registerRQ.getPublicKey());
        userEntity.setPassword(registerRQ.getPassword());
        userRepo.save(userEntity);
        registerRS.setStatus("Your account has been successfully created");
        return registerRS;
    }

    @Override
    public LoginRS login(LoginRQ loginRQ) {
        UserEntity user=userRepo.findByPublicKey(loginRQ.getPublicKey());
        LoginRS loginRS=new LoginRS();
        if(user==null||!user.getPassword().equals(loginRQ.getPassword())){
            loginRS.setStatus("User not exists.");
            loginRS.setJwt("");
            return loginRS;
        }
        String jwt=jwtService.generateToken(loginRQ.getPublicKey());
        loginRS.setStatus("Success!");
        loginRS.setJwt(jwt);
        return loginRS;
    }

    @Override
    public LoggedInRS getUserLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoggedInRS loggedInRS=new LoggedInRS();
        if (authentication == null) {
            loggedInRS.setStatus("User not authenticated");
            loggedInRS.setUserEntity(new UserEntity());
            return loggedInRS;
        }

        UserEntity userEntity = userRepo.findByPublicKey(authentication.getName());
        if (userEntity == null) {
            loggedInRS.setStatus("User not found");
            loggedInRS.setUserEntity(new UserEntity());
        }
        loggedInRS.setStatus("Success!");
        loggedInRS.setUserEntity(userEntity);
        return loggedInRS;
    }
}
