//package com.example.survey_tourist.config.jwt;
//
//import com.example.survey_tourist.dto.UserPrincipal;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//import java.io.Serializable;
//import java.util.*;
//import java.util.function.Function;

//@Component
//public class JwtTokenUtil implements Serializable {
//    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
//    @Value("Private KEY")
//    private String secret;
//
//    public String getUsernameFromToken(String token){
//
//        return getClaimFromToken(token, Claims::getSubject);
//    }
//
//
//
//    public Date getExpirationDateFromToken(String token) {
//        return getClaimFromToken(token, Claims::getExpiration);
//    }
//
//
//    public <T> T getClaimFromToken(String token, Function<Claims,T> claimsResolver){
//        final Claims claims = getAllClaimsFromToken(token);
//        return claimsResolver.apply(claims);
//    }
//
//    private Claims getAllClaimsFromToken(String token){
//        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
//    }
//    private Boolean isTokenExpired(String token){
//        final Date expiration = getExpirationDateFromToken(token);
//        return expiration.before(new Date());
//    }
//
//    public String generateToken(UserPrincipal userPrincipal) {
//        return doGenerateToken(userPrincipal);
//    }
//
//    private String doGenerateToken(UserPrincipal userPrincipal ){
//        Map<String,Object> claims = new HashMap<>();
//
//        claims.put("userName",userPrincipal.getUsername());
//        claims.put("nameRole",userPrincipal.getAuthorities());
//        return Jwts.builder().setClaims(claims).setSubject(userPrincipal.getUsername()).setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY* 1000))
//                .addClaims(claims).signWith(SignatureAlgorithm.HS512,secret).compact();
//    }
//
//    public Boolean validateToken(String token ,UserPrincipal  userPrincipal){
//        final String username = getUsernameFromToken(token);
//        return (username.equals(userPrincipal.getUsername()) && !isTokenExpired(token));
//    }
//}