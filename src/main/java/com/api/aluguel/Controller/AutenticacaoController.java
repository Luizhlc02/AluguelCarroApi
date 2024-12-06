package com.api.aluguel.Controller;

import com.api.aluguel.Security.MyAuthenticationProvider;
import com.api.aluguel.Service.AuthenticationService;
import com.api.aluguel.dto.ClienteLoginDto;
import com.api.aluguel.dto.TokenResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping
public class AutenticacaoController {
    @Autowired
    private final AuthenticationService authenticationService;

    @Autowired
    private final AuthenticationManager authenticationManager;

    @PostMapping("/auth")
    public TokenResponseDto authenticate(@RequestBody ClienteLoginDto dto){
      UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getSenha());
      Authentication authentication = authenticationManager.authenticate(authenticationToken);
      return authenticationService.authenticate(dto.getEmail(), dto.getSenha());
  }
}
