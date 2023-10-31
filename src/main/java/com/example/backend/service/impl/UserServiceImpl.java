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
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserRepo userRepo;

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
        String username=loginRQ.getPublicKey();
        String password=loginRQ.getPassword();
        LoginRS loginRS=new LoginRS();
        UserEntity userEntity=userRepo.findByPublicKey(username);
        if(userEntity==null||!password.equals(userEntity.getPassword())){
            loginRS.setStatus("Failed, wrong Username or password. Please try again or register new account!");
            return loginRS;
        }
        String jwt=jwtService.generateToken(loginRQ.getPublicKey());
        loginRS.setStatus("Success!");
        loginRS.setJwt(jwt);
        return loginRS;
    }

    @Override
    public LoggedInRS getUserLoggedIn(String jwt) {
        LoggedInRS loggedInRS=new LoggedInRS();
        if(!jwtService.validateToken(jwt)){
            loggedInRS.setStatus("Session has expired. Please log in again!");
            return loggedInRS;
        }
        String publicKey=jwtService.getUsernameFromJWT(jwt);
        UserEntity userEntity=userRepo.findByPublicKey(publicKey);
        loggedInRS.setStatus("Success");
        loggedInRS.setUserEntity(userEntity);
        return loggedInRS;
    }
}
