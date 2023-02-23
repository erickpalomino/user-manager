package com.epg.user.manager.controllers;

import com.epg.user.manager.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/status")
public class Status {
    @GetMapping("/")
    public ResponseEntity<Object> getAppStatus(){
        Map response=new HashMap<String,String>();
        response.put("status","OK");
        return new ResponseEntity(response, null,HttpStatus.OK);
    }

}
