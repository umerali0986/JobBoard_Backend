package com.umerscode.Jobboard.Dto;

import lombok.Data;

@Data
public class AuthenticationRequest {

    private String email;
    private String password;
}
