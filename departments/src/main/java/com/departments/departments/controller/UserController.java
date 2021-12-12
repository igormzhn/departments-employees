package com.departments.departments.controller;

import com.departments.departments.model.MyUser;
import com.departments.departments.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping(value = "/registration")
    public ResponseEntity<?> register(@RequestBody MyUser myUser){
        userDetailsService.register(myUser);
        return ResponseEntity.ok().build();
    }
}
