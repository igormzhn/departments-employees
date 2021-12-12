package com.employees.employeesservice.service;

import com.employees.employeesservice.integration.IntegrationDepartmentService;
import com.employees.employeesservice.repository.EmployeeRepository;
import com.employees.employeesservice.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private IntegrationDepartmentService departmentIntegrationService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Integer create(Employee employee){
        if(departmentIntegrationService.ifDepartmentsExists(employee.getDepartmentId())){
            Employee newEmp = employeeRepository.save(employee);
            return  newEmp.getId();
        }
        throw new IllegalArgumentException();

    }

    @Override
    public List<Employee> readAll() {
        return employeeRepository.findAll();
    }

    /*@Override
    public List<Employee> getEmployeeByDepartmentId(int departmentId){
        return employeeRepository.getById(departmentId);
    }*/

    @Override
    public Employee read(int id) {
        return employeeRepository.getById(id);
        //return EMPLOYEE_MAP.get(id);
    }

    @Override
    public boolean update(Employee employee, int id) {
        if (employeeRepository.existsById(id)) {
            employee.setId(id);
            employeeRepository.save(employee);
            return true;
        }

        return false;
    }


    @Override
    public boolean delete(int id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);

            return true;
        }

        return false;
    }

    @Override
    @Transactional
    public void move(int from, int to) {
        final List<Employee> lst = employeeRepository.findByDepartmentId(from);
        lst.stream().forEach(employee -> {
            employee.setDepartmentId(to);
        });
        //TODO: проверить правильность сохранения departmentId для каждого employee при закомментировать следующей строки
        employeeRepository.saveAllAndFlush(lst);

    }


}
