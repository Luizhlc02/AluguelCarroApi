/*

package com.api.aluguel.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Slf4j
public class JwtAuthorizationFilter extends OncePerRequestFilter {


    private JwtUserDetailsService detailsService;


    private void toAuthentication(HttpServletRequest request, String emailCliente){
        UserDetails userDetails = detailsService.loadUserByUsername(emailCliente);
        UsernamePasswordAuthenticationToken authenticationToken = UsernamePasswordAuthenticationToken
                .authenticated(userDetails,null,userDetails.getAuthorities());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        
        String token = retrieveToken(request);
        log.info("Token criado: {}", token);
        if(token == null || !token.startsWith(JwtUtils.JWT_BEARER)){
            log.info("JWT Token está nulo ou não foi iniciado");
            filterChain.doFilter(request, response);
            return;
        }

        if (!JwtUtils.tokenValido(token)) {
            log.warn("JWT token está invalido ou expirado");
            filterChain.doFilter(request, response);
            return;
        }

        String emailCliente = JwtUtils.getEmailFromToken(token);

        toAuthentication(request,emailCliente);

        filterChain.doFilter(request,response);
    }

    private String retrieveToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if(token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return null;
        }
        return token.substring(7, token.length());
    }




}
*/
