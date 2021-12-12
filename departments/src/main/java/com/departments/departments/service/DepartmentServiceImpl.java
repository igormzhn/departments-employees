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

    //private static final Map<Integer, Departments> DEPARTMENT_REPOSITORY_MAP = new HashMap<>();
    //private static final AtomicInteger DEPARTMENT_ID_HOLDER = new AtomicInteger();

    //@Autowired
    private final DepartmentRepository departmentRepository;
    //private DepartmentRepository departmentRepository1;

    //@Autowired
    private final IntegrationEmployeeService intagrationEmployeeService;

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
      //return new ArrayList<>(DEPARTMENT_REPOSITORY_MAP.values());
    }

      @Override
      public Departments read(int id){
        return departmentRepository.getById(id);
        //return DEPARTMENT_REPOSITORY_MAP.get(id);
    }

      /*@Override
      public List<Departments> readById(){
        return departmentRepository.getById();
    }*/

    @Override
    public boolean update(Departments departments, int id){
        if (departmentRepository.existsById(id)){
            departments.setId(id);
            departmentRepository.save(departments);
            //DEPARTMENT_REPOSITORY_MAP.put(id, departments);
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
        intagrationEmployeeService.moveEmployee(moveEmployeeDto);
        return true;
    }
}
