package com.epg.user.manager.controllers;

import com.epg.user.manager.models.User;
import com.epg.user.manager.models.dto.LoginRequestDto;
import com.epg.user.manager.models.dto.RegisterRequestDto;
import com.epg.user.manager.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<Object> signUp(@RequestBody RegisterRequestDto registerDto){
        authenticationService.register(registerDto);
        Map<String,String> response = new HashMap<>();
        response.put("message","User Registered");
        return new ResponseEntity(response, null, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequestDto loginDto){
        User user =authenticationService.login(loginDto);
        Map<String,Object> response = new HashMap<>();
        response.put("message","User Logged In successfully");
        response.put("data", user);
        HttpHeaders header= new HttpHeaders();
        header.set("Authorization",String.format("Bearer %s",authenticationService.generateToken(user)));
        return new ResponseEntity<>(response,header,HttpStatus.OK);
    }
}
