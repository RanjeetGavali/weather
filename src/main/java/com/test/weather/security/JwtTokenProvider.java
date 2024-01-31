package com.test.weather.security;

import com.test.weather.exception.WeatherServiceException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;


@Slf4j
@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String jwtSecreteKey;


    @PostConstruct
    protected void init() {
        jwtSecreteKey = Base64.getEncoder().encodeToString(jwtSecreteKey.getBytes());
    }

    public String createToken(Long id) {
        Long jwtExpiration = 5 * 60 * 60 * 1000L;
        Date now = new Date();
        Date validity = new Date(now.getTime() + jwtExpiration);
        Claims claims = Jwts.claims().setSubject(String.valueOf(id));
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, jwtSecreteKey)
                .compact();
    }

    public boolean validateToken(String token, HttpServletRequest request) {
        try {
            Claims claims = getClaimsByToken(token);
            if (!resolveId(claims, request)) {
                throw new WeatherServiceException("Id mismatch", HttpStatus.UNAUTHORIZED.value());
            }

            return true;

        } catch (Exception e) {
            throw new WeatherServiceException("Expired or invalid JWT token", HttpStatus.UNAUTHORIZED.value());
        }
    }

    public Authentication getAuthentication(String token) {
        Claims claims = getClaimsByToken(token);
        String id = claims.getSubject();
        UserDetails userDetails = new User(id, id, new ArrayList<>());

        return new UsernamePasswordAuthenticationToken(userDetails, id, userDetails.getAuthorities());
    }

    private boolean resolveId(Claims claims, HttpServletRequest request) {
        String id = request.getHeader("id");
        String tokenId = claims.getSubject();

        return id != null && !id.isEmpty() && id.equals(tokenId);
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public Claims getClaimsByToken(String token) throws JwtException {
        return Jwts.parser().setSigningKey(jwtSecreteKey).parseClaimsJws(token).getBody();
    }

}
