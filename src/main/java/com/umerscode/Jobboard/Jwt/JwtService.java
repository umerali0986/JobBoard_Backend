package com.umerscode.Jobboard.Jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.umerscode.Jobboard.Repository.AppUserRepo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class JwtService {


    private final AppUserRepo userRepo;
//    private final static String SECRET_KEY = String.valueOf(MacProvider.generateKey());
//
//
//    public String getUsername(String token){
//        return extractClaim(token,Claims::getSubject);
//    }
//
//    public Date getExpirationDate(String token){
//        return extractClaim(token,Claims::getExpiration);
//    }
//
//    public <T> T extractClaim(String token, Function<Claims,T> claimResolver){
//        Claims claims = extractAllClaims(token);
//        return claimResolver.apply(claims);
//    }
//
//
//    public Claims extractAllClaims(String token){
//        return Jwts.parser()
//                .setSigningKey(SECRET_KEY)
//                .parseClaimsJws(token)
//                .getBody();
//    }

    public String generateJwt(UserDetails userDetails){
        return createJwt(new HashMap<>(),userDetails.getUsername());
    }

    private String createJwt(HashMap<Object, Object> map, String email) {
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());

       return JWT.create()
                .withSubject(email)
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis()+1000 *60 *24))
                .withIssuer(email)
                .withClaim("roles", userRepo.findUserByEmail(email).get().getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);


//        return Jwts.builder()
//                .setSubject(email)
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setIssuer(email)
////                .setClaims("roles", userRepo.findUserByEmail(email).get().getRole().name())
//                .setExpiration(new Date(System.currentTimeMillis()+1000 *60 *24))
//                .signWith(SignatureAlgorithm.HS256,SECRET_KEY)
//                .compact();
    }

//    public boolean isTokenValid(String token, UserDetails userDetails){
//        String username = getUsername(token);
//
//        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//    }
//
//    private boolean isTokenExpired(String token) {
//        return getExpirationDate(token).before(new Date());
//    }


}
