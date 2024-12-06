package com.api.aluguel.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.stream.Collectors;

@Service
public class JwtService {
    private final JwtEncoder jwtEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    public JwtService(JwtEncoder encoder){
        this.jwtEncoder = encoder;
    }

    public String generateToken(Authentication authentication){
        Instant issuedAt = Instant.now();
        Long expiresIn = 300L;

        String scopes = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        var token = JwtClaimsSet.builder()
                .issuer("spring-security-jwt")
                .subject(authentication.getName())
                .issuedAt(issuedAt)
                .claim("Scope", scopes)
                .expiresAt(issuedAt.plusSeconds(expiresIn))
                .build();
        return jwtEncoder.encode(JwtEncoderParameters.from(token)).getTokenValue();
    }

}
