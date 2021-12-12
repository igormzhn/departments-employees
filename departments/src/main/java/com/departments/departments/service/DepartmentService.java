package com.departments.departments.service;

import com.departments.departments.dto.MoveEmployeeDto;
import com.departments.departments.model.Departments;

import java.util.List;

public interface DepartmentService {

    int create (Departments departments);

    List<Departments> readAll();

    Departments read(int id);

    //List<Departments> readById(int id);

    //List<Departments> readTitle();

    boolean update (Departments departments, int id);

    boolean delete (MoveEmployeeDto moveEmployeeDto);


}
