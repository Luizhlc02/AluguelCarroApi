package com.api.aluguel.Service;

import com.api.aluguel.Security.JwtService;
import com.api.aluguel.dto.TokenResponseDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;


@Service
public class AuthenticationService {
    private AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthenticationService(AuthenticationManager authenticationManager,JwtService jwtService) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public TokenResponseDto authenticate(String email, String senha){
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(email, senha);
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        String jwt = jwtService.generateToken(authentication);
        return TokenResponseDto.builder()
                .accessToken(jwt)
                .build();
    }


}
