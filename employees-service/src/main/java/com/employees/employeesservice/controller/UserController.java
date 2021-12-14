package com.employees.employeesservice.controller;

import com.employees.employeesservice.model.MyUser;
import com.employees.employeesservice.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

    @Autowired
    private  UserDetailsServiceImpl userDetailsService;

    @PostMapping(value = "/registration")
    public ResponseEntity<?> register(@RequestBody MyUser myUser){
        userDetailsService.register(myUser);
        return ResponseEntity.ok().build();
    }
}
