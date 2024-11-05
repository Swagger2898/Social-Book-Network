package com.swagger.Social_Book_Network.security;

import jakarta.validation.constraints.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;

import static java.util.Arrays.stream;

@Service
public class JwtService {

    private long jwtExpiration;

    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }

    private <V,K> String generateToken(HashMap<String, Object> claims, UserDetails userDetails){
        return buildTokens(claims,userDetails,jwtExpiration);

    }

    private String buildTokens(HashMap<String, Object> claims,
                               UserDetails userDetails,
                               Object jwtExpiration) {
        var authorities = userDetails.getAuthorities()
        .stream().map(GrantedAuthority::getAuthority)
                .toList();
        return jwts
                .builder()
                .setClaims(extractClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()))+jwtExpiration);

    }


    public String extractUsername(String jwt) {
    }


}
