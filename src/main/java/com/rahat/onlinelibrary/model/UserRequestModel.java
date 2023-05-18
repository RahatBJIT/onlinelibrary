package com.rahat.onlinelibrary.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestModel {
    private String address;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String role;
}
