package com.employees.employeesservice.controller;

import com.employees.employeesservice.dto.MoveDepartmentDto;
import com.employees.employeesservice.model.Employee;
import com.employees.employeesservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @PostMapping(value = "/employees")
    public ResponseEntity<?> create (@RequestBody Employee employee){
        employeeService.create(employee);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/employees")
    public ResponseEntity<List<Employee>> readAll(){

        final List<Employee> employees = employeeService.readAll();

        return employees != null && !employees.isEmpty()
                ? new ResponseEntity<>(employees, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/employees/{id}")
    public ResponseEntity<Employee> update(@RequestBody Employee employee, @PathVariable(name = "id") int id){
         boolean res = employeeService.update(employee, id);

         return res == true
                 ? new ResponseEntity<>(HttpStatus.OK)
                 : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/employees/{id}")
    public ResponseEntity<Employee> read(@PathVariable(name = "id") int id){
        final Employee employee = employeeService.read(id);

        return employee != null
                ? new ResponseEntity<>(employee, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/employees/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id){
        boolean deleted = employeeService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PostMapping(value = "/employees/move")//обработка исключений
    public ResponseEntity<?> move(@RequestBody MoveDepartmentDto moveDepartmentDto){
           employeeService.move(moveDepartmentDto.getFromDepartmentId(), moveDepartmentDto.getToDepartmentId());
           return ResponseEntity.ok().build();
    }
}
