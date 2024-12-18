package com.api.aluguel.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Slf4j
public class JwtUtils {

    private static final String JWT_BEARER = "Bearer ";
    private static final String JWT_AUTHORIZATION = "Authorization";
    private static final String SECRET_KEY = "0123456789-012345679-0123456789";
    private static final long EXPIRE_DAYS = 0;
    private static final long EXPIRE_HOURS = 0;
    private static final long EXPIRE_MINUTES = 2;

    private JwtUtils(){
    }

    private static Key generateKey(){
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }
    private static Date toExpireData(Date start){
        LocalDateTime dateTime = start.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime end = dateTime.plusDays(EXPIRE_DAYS).plusHours(EXPIRE_HOURS).plusHours(EXPIRE_MINUTES);
        return Date.from(end.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static JwtToken createToken(String emailCliente, String role){
        //data de criação do TOken
        Date issuedAt = new Date();
        Date limite = toExpireData(issuedAt);

        String token = Jwts.builder()
                .setHeaderParam("typ","JWT")
                .setSubject(emailCliente)
                .setIssuedAt(issuedAt)
                .setExpiration(limite)
                //assinatura do token recebe a key e a criptografia
                .signWith(generateKey(), SignatureAlgorithm.ES256)
                .claim("role",role)
                .compact();
        return new JwtToken(token);
    }

    private static Claims getClaimsFromToken(String token){
        try {
            return Jwts.parser()
                    .setSigningKey(generateKey()).build()
                    .parseClaimsJws(refactorToken(token)).getBody();
        }
        catch (JwtException exception){
            log.error(String.format("Token invalido %s", exception.getMessage()));
        }
        return null;
    }

    public static boolean tokenValido(String token){

        try {
            Jwts.parser()
                    .setSigningKey(generateKey()).build()
                    .parseClaimsJws(refactorToken(token));
            return true;
        }
        catch (JwtException exception){
            log.error(String.format("Token invalido %s", exception.getMessage()));
        }
        return false;
    }

    private static String refactorToken(String token){
        if (token.contains(JWT_BEARER)){
            return token.substring(JWT_BEARER.length());
        }
        return token;
    }

}
