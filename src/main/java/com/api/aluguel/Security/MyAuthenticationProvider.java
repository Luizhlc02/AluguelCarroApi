package com.api.aluguel.Security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

public class MyAuthenticationProvider implements AuthenticationProvider {
    Logger logger = LoggerFactory.getLogger(MyAuthenticationProvider.class);

    @Autowired
    private JwtUserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getPrincipal().toString();
        String senha = authentication.getCredentials().toString();
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        if (!passwordEncoder.matches(senha, userDetails.getPassword())) {
            throw new BadCredentialsException("Senha incorreta");
        }
        return new UsernamePasswordAuthenticationToken(email, userDetails.getPassword());
    }
    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
