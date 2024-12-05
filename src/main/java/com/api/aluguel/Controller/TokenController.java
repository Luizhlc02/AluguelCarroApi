/*
package com.api.aluguel.Controller;


import com.api.aluguel.Repository.ClienteRepository;
import com.api.aluguel.dto.LoginClienteRequest;
import com.api.aluguel.dto.LoginResponse;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;


@RestController
public class TokenController {

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ClienteRepository clienteRepository;


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginClienteRequest clienteRequest){
      var cliente =  clienteRepository.findByemailCliente(clienteRequest.emailCliente());
        if (cliente.isEmpty() || !cliente.get().isLoginCorrect(clienteRequest, bCryptPasswordEncoder)){
            throw new BadCredentialsException("Email ou senha invalido");
        }
        var issuedAt = Instant.now();
        var expiresIn = 300L;
        var token = JwtClaimsSet.builder()
                .subject(cliente.get().getEmailCliente().toString())
                .issuedAt(issuedAt)
                .expiresAt(issuedAt.plusSeconds(expiresIn))
                .build();



    }
}
*/
