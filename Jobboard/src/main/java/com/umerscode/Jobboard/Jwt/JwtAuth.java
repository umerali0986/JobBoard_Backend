package com.umerscode.Jobboard.Jwt;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component @Getter @Setter
public class JwtAuth {

    private String message = "Invalid email or password";
    private String jwt;

    private String role;
}
