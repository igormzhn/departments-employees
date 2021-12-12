
package com.employees.employeesservice.service;

import com.employees.employeesservice.model.MyUser;
import com.employees.employeesservice.repository.MyUserRepository;
import com.employees.employeesservice.repository.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserService {
    @Autowired
    private  MyUserRepository myUserRepository;

    @Autowired
    private  PasswordEncoder passwordEncoder;

    @Override
    public Integer register(MyUser myUser) {
        System.out.println("Metod register");
        MyUser existedUser = myUserRepository.findByUsername(myUser.getUsername());
        if(existedUser == null){
            existedUser = new MyUser();
            existedUser.setUsername(myUser.getUsername());
        }
        existedUser.setPassword(passwordEncoder.encode(myUser.getPassword()));
        existedUser.setAuthority(myUser.getAuthority());
        myUserRepository.save(existedUser);
        return existedUser.getId();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Metod loadUserByUsername");
        MyUser myUser = myUserRepository.findByUsername(username);
        if (myUser == null) {
            throw new UsernameNotFoundException("Unknown user: "+username);
        }
        UserDetails userDetails = User.builder()
                .username(myUser.getUsername())
                .password(myUser.getPassword())
                .roles(myUser.getAuthority())
                .build();
        return userDetails;
    }
}


