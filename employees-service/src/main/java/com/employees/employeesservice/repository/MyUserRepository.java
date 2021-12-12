package com.employees.employeesservice.repository;

import com.employees.employeesservice.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyUserRepository extends JpaRepository<MyUser,Integer>{
    MyUser findByUsername(String username);
}
