package com.picpayclone.dto;

import lombok.Data;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.NotBlank;

@Data
public class LoginDTO {

    // Attributes
    @NotBlank
    private String user;

    @NotBlank
    private String password;

    // Method
    public UsernamePasswordAuthenticationToken converter() {
        return new UsernamePasswordAuthenticationToken(user, password);
    }
}
