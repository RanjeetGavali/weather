package com.test.weather.controller;

import com.test.weather.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class JwtTokenController {
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @GetMapping("/jwt/token/{id}")
    public ResponseEntity<?> getJwtTokenById(@PathVariable Long id) {
        return ResponseEntity.ok(jwtTokenProvider.createToken(id));

    }
}
