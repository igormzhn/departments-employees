package com.departments.departments.repository;

import com.departments.departments.model.MyUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
   Integer register(MyUser myUser);
}
