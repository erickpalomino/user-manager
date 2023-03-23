package com.epg.user.manager.config.security;

import com.epg.user.manager.models.User;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.Time;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class JwtUtilTest {
    User testUser;
    String token;
    JwtUtil jwtUtil;

    @BeforeEach
    void setUp() {
        jwtUtil=new JwtUtil();
        testUser= User.builder().userName("test").userPassword("test").userEmail("test@gmail.com").build();
        token=jwtUtil.generateToken(testUser);
    }

    @Test
    void extractUsername() {
        Assertions.assertEquals(jwtUtil.extractUsername(token),testUser.getUsername());
    }

    @Test
    void extractExpiration() {
        Assertions.assertTrue(jwtUtil.extractExpiration(token).after(Date.from(Instant.now())));
    }

    @Test
    void extractClaim() {
        Assertions.assertEquals(jwtUtil.extractClaim(token, Claims::getSubject),testUser.getUsername());
    }

    @Test
    void generateToken() {

    }

    @Test
    void validateToken() {
    }
}