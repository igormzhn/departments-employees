package com.employees.employeesservice.repository;

import com.employees.employeesservice.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

    List<Employee> findByDepartmentId(int from);
}
