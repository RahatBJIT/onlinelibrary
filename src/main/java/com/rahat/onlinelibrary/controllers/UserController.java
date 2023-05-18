package com.rahat.onlinelibrary.controllers;

import com.rahat.onlinelibrary.model.UserRequestModel;
import com.rahat.onlinelibrary.model.AuthenticationResponse;
import com.rahat.onlinelibrary.model.AuthenticationRequest;
import com.rahat.onlinelibrary.service.UserService;
import org.springframework.http.HttpStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody UserRequestModel requestModel){
        return userService.register(requestModel);
    }
    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody AuthenticationRequest authenticationRequest){
        return userService.login(authenticationRequest);
    }

}