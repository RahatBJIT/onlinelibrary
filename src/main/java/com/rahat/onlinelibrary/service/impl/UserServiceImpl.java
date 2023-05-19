package com.rahat.onlinelibrary.service.impl;

import com.rahat.onlinelibrary.entity.Role;
import com.rahat.onlinelibrary.entity.UserEntity;
import com.rahat.onlinelibrary.exception.EmailPasswordNotMatchException;
import com.rahat.onlinelibrary.exception.UserAlreadyExistException;
import com.rahat.onlinelibrary.model.AuthenticationRequest;
import com.rahat.onlinelibrary.model.AuthenticationResponse;
import com.rahat.onlinelibrary.model.UserRequestModel;
import com.rahat.onlinelibrary.repository.UserRepository;
import com.rahat.onlinelibrary.service.UserService;
import com.rahat.onlinelibrary.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public ResponseEntity<Object> register(UserRequestModel requestModel) {

        UserEntity userExistedAlready = userRepository.findByEmail(requestModel.getEmail());
        if (userExistedAlready != null) {
            throw new UserAlreadyExistException("This Email Already Existed");

        }
        UserEntity userEntity = UserEntity.builder()
                .email(requestModel.getEmail())
                .address(requestModel.getAddress())
                .firstName(requestModel.getFirstName())
                .lastName(requestModel.getLastName())
                .password(passwordEncoder.encode(requestModel.getPassword()))
//                .roles(Objects.equals(requestModel.getRole(), "ADMIN") ?(Role.ADMIN,Role.CUSTOMER):Role.CUSTOMER)
                .role1(Objects.equals(requestModel.getRoles().get(0), "ADMIN") ? Role.ADMIN : Role.CUSTOMER)
                .role2(requestModel.getRoles().size()==2?(

                        Objects.equals(requestModel.getRoles().get(1), "ADMIN") ? Role.ADMIN : Role.CUSTOMER):(Objects.equals(requestModel.getRoles().get(0), "ADMIN") ? Role.ADMIN : Role.CUSTOMER))
                .build();
        userRepository.save(userEntity);
        AuthenticationResponse authRes = AuthenticationResponse.builder()
                .token(jwtService.generateToken(userEntity))
                .build();
        return new ResponseEntity<>(authRes, HttpStatus.CREATED);
    }

    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getEmail(),
                            authenticationRequest.getPassword()
                    )
            );
        } catch (Exception e) {
            throw new EmailPasswordNotMatchException("Wrong Email And Password Information");

        }


        var user = userRepository.findByEmail(authenticationRequest.getEmail());

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

    }
}
