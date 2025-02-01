package com.springsecuritydemo.springsecurityapplication.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {
  private static final String SECRETE_KEY = "12345678";
  private static final int TOKEN_VALIDITY = 3600 * 5;


  public String getUsernameFromToken(String token){
    return getClaimFromToken(token, Claims::getSubject);
  }

  public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = getAllClaimsFromToken(token);
    return claimsResolver.apply(claims);
  }

  public Claims getAllClaimsFromToken(String token) {
    return Jwts.parser().setSigningKey(SECRETE_KEY).parseClaimsJws(token).getBody();
  }

  public Boolean isValidateToken(String token, UserDetails userDetails){
    final String username = getUsernameFromToken(token);
    return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
  }

  public boolean isTokenExpired(String token) {
    final Date expiration = getClaimFromToken(token, Claims::getExpiration);
    return expiration.before(new Date());
  }

  public String generateToken(UserDetails userDetails){
    Map<String, Object> claims = new HashMap<>();
    return createToken(claims,userDetails);
  }

  public String createToken(Map<String, Object> claims, UserDetails userDetails){
    return Jwts.builder()
            .setClaims(claims)
            .setSubject(userDetails.getUsername())
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis()+ TOKEN_VALIDITY * 1000) )
            .signWith(SignatureAlgorithm.HS512, SECRETE_KEY)
            .compact();
  }
}
