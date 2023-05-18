package com.rahat.onlinelibrary.service;

import com.rahat.onlinelibrary.model.AuthenticationRequest;
import com.rahat.onlinelibrary.model.AuthenticationResponse;
import com.rahat.onlinelibrary.model.UserRequestModel;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<Object> register(UserRequestModel requestModel);

    AuthenticationResponse login(AuthenticationRequest authenticationRequest);
}

