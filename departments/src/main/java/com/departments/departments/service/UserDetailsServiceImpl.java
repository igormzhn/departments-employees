package com.departments.departments.service;

import com.departments.departments.model.MyUser;
import com.departments.departments.repository.MyUserRepository;
import com.departments.departments.repository.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserService {
    @Autowired
    private MyUserRepository userRepository;

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Override
    public Integer register(MyUser myUser){
        MyUser existedUser = userRepository.findByUsername(myUser.getUsername());
        if(existedUser == null){
            existedUser = new MyUser();
            existedUser.setUsername(myUser.getUsername());
        }
        existedUser.setPassword(passwordEncoder.encode(myUser.getPassword()));
        existedUser.setAuthority(myUser.getAuthority());
        userRepository.save(existedUser);
        return existedUser.getId();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        MyUser myUser = userRepository.findByUsername(username);
        if(myUser == null){
            throw new UsernameNotFoundException("Unknown user: " + username);
        }
        UserDetails userDetails = User.builder()
                .username(myUser.getUsername())
                .password(myUser.getPassword())
                .roles(myUser.getAuthority())
                .build();
        return userDetails;
    }
}
