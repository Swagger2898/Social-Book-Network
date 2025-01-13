package com.swagger.Social_Book_Network.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

import static java.util.Arrays.stream;

@Service
public class JwtService {


    @Value(value = "${application.security.jwt.secret-key}")
    private String secretKey = "1qouwehfaoiuwfauokif1234567890abcd"; // 32 characters (256 bits)



    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration = 8640000;

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims,T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                ;

    }


    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }

    public <V,K> String generateToken(HashMap<String, Object> claims, UserDetails userDetails){
        return buildTokens(claims,userDetails,jwtExpiration);

    }

    private String buildTokens(HashMap<String, Object> claims,
                               UserDetails userDetails,
                               long jwtExpiration) {
        var authorities = userDetails.getAuthorities()
        .stream().map(GrantedAuthority::getAuthority)
                .toList();
        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+jwtExpiration))
                .claim("authorities",authorities)
                .signWith(getSignInKey())
                .compact();



    }


    public boolean isTokenValid(String token , UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {

        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token,Claims::getExpiration);
    }

    private Key getSignInKey() {
        // Ensure the key is exactly 32 bytes (256 bits)
        byte[] keyBytes = secretKey.substring(0, 32).getBytes(StandardCharsets.UTF_8); // Trim to 32 characters



        // Ensure the key is 256 bits (32 bytes)
        if (keyBytes.length != 32) {
            throw new IllegalArgumentException("The key must be 256 bits (32 bytes).");
        }

        return Keys.hmacShaKeyFor(keyBytes);
    }
    }


















