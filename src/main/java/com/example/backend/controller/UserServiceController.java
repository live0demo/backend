package com.example.backend.controller;

import com.example.backend.api.request.LoginRQ;
import com.example.backend.api.request.RegisterRQ;
import com.example.backend.api.response.LoggedInRS;
import com.example.backend.common.response.LangResponseBuilder;
import com.example.backend.common.response.Response;
import com.example.backend.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class UserServiceController {


    @Autowired
    private UserService userService;

    @ApiOperation(value = "API for register new account")
    @PostMapping("/register")
    public Response registerNewAccount(@RequestBody RegisterRQ registerRQ) {
        return LangResponseBuilder.success(userService.registerAccount(registerRQ));
    }

    @PostMapping("/login")
    public Response login(@RequestBody LoginRQ loginRQ) {
        return LangResponseBuilder.success(userService.login(loginRQ));
    }

//    @PostMapping("/logout")
//    public Response logoutUser(HttpServletRequest request, HttpServletResponse response) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null) {
//            new SecurityContextLogoutHandler().logout(request, response, authentication);
//        }
//
//        return LangResponseBuilder.successWithLang("Logged out successfully!");
//    }

    @GetMapping("/me")
    public ResponseEntity<?> getLoggedInUser(@RequestParam String jwt) {
        LoggedInRS loggedInRS = userService.getUserLoggedIn(jwt);
        if (loggedInRS.getStatus().equals("User not authenticated")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
        }
        if (loggedInRS.getStatus().equals("User not found")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        return ResponseEntity.ok(loggedInRS.getUserEntity());
    }


}
