package com.employees.employeesservice.repository;

import com.employees.employeesservice.model.MyUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    Integer register(MyUser myUser);
}
