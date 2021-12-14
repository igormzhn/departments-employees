package com.departments.departments.service;

import com.departments.departments.dto.MoveEmployeeDto;
import com.departments.departments.exception.DepartmentNotFoundException;
import com.departments.departments.integration.IntegrationEmployeeService;
import com.departments.departments.model.Departments;
import com.departments.departments.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService{

    //@Autowired
    private final DepartmentRepository departmentRepository;


    //@Autowired
    private final IntegrationEmployeeService integrationEmployeeService;

    @Override
    public int create(Departments departments){
       try{
            Departments department = departmentRepository.save(departments);
            return department.getId();
        }catch (DataAccessException e){
            log.error(String.format("Unable to create department with name: %s", departments.getTitle()), e);
            throw e;
        }
    }

      @Override
      public List<Departments> readAll(){
        return departmentRepository.findAll();
       }

      @Override
      public Departments read(int id){
        return departmentRepository.getById(id);
            }

    @Override
    public boolean update(Departments departments, int id){
        if (departmentRepository.existsById(id)){
            departments.setId(id);
            departmentRepository.save(departments);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(MoveEmployeeDto moveEmployeeDto){
        int departmentId = moveEmployeeDto.getFromDepartmentId();
        if(!departmentRepository.existsById(departmentId)) {
            throw new DepartmentNotFoundException();
        }
        departmentRepository.deleteById(departmentId);
        integrationEmployeeService.moveEmployee(moveEmployeeDto);
        return true;
    }
}
