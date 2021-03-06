package com.picpayclone.resource;

import com.picpayclone.dto.LoginDTO;
import com.picpayclone.dto.TokenDTO;
import com.picpayclone.service.impl.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/authentication")
public class AuthenticationResource extends ResourceBase<TokenDTO> {

    // Attributes
    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    // Method
    @PostMapping
    public ResponseEntity<TokenDTO> authenticate(@RequestBody @Valid LoginDTO loginDTO) {
        UsernamePasswordAuthenticationToken loginData = loginDTO.converter();

        try {
            Authentication authentication = authManager.authenticate(loginData);
            String token = tokenService.tokenGenerate(authentication);
            return responseItemSuccess(new TokenDTO(token, "Bearer"));
        } catch (AuthenticationException e) {
            return responseBadRequest();
        }
    }

}
