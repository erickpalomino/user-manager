package com.epg.user.manager.services;

import com.epg.user.manager.models.User;
import com.epg.user.manager.models.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public User getUserByUserName(String userName){
        return userRepository.findByUserName(userName);
    }

}
