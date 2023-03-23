package com.epg.user.manager.services;

import com.epg.user.manager.config.security.JwtUtil;
import com.epg.user.manager.models.User;
import com.epg.user.manager.models.dto.LoginRequestDto;
import com.epg.user.manager.models.dto.RegisterRequestDto;
import com.epg.user.manager.models.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder encoder= new BCryptPasswordEncoder();

    public void register(RegisterRequestDto registerDto) {
        System.out.println(registerDto);
        User newUser = User.builder()
                .userName(registerDto.getUserName())
                .userPassword(encoder.encode(registerDto.getUserPassword()))
                .userEmail(registerDto.getUserEmail())
                .build();
        userRepository.save(newUser);
    }

    public User login(LoginRequestDto loginDto){
        User identifiedMyUserDetails =userRepository.findByUserName(loginDto.getUserName());
        if (identifiedMyUserDetails ==null)
            return null;
        else if (encoder.matches(loginDto.getUserPassword(), identifiedMyUserDetails.getPassword()))
            return identifiedMyUserDetails;
        else
            return null;
    }

    public String generateToken(User user){
        return jwtUtil.generateToken(user);
    }



}
