package com.employees.employeesservice.service;

import com.employees.employeesservice.model.Employee;

import java.util.List;

public interface EmployeeService {
    Integer create (Employee employee);
    //List<Employee> getEmployeeByDepartmentId(int departmentId);
    List<Employee> readAll();
    Employee read(int id);
    boolean update (Employee employee, int id);
    boolean delete(int id);
    void move(int from, int to);

}
