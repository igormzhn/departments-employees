package com.departments.departments.controller;

import com.departments.departments.dto.MoveEmployeeDto;
import com.departments.departments.model.Departments;
import com.departments.departments.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/departments")
@Slf4j
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService){
        this.departmentService = departmentService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody Departments departments){
        int departmentId = departmentService.create(departments);
        return ResponseEntity.created(URI.create("http://localhost:8081/v1/departments/" + departmentId)).build();
    }

    @GetMapping
    public ResponseEntity<List<Departments>> read(){
        final List<Departments> departments = departmentService.readAll();

        return  departments != null && !departments.isEmpty()
              ? new ResponseEntity<>(departments, HttpStatus.OK)
              : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Departments> read (@PathVariable(name = "id") int id){
        final Departments departments = departmentService.read(id);

        return departments != null
                ? new ResponseEntity<>(departments, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<?> update (@PathVariable(name = "id") int id, @RequestBody Departments departments){
        final  boolean update = departmentService.update(departments, id);

        return  update
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id, @RequestParam("depToMove") int depToMove){
        final boolean deleted = departmentService.delete(MoveEmployeeDto.builder().fromDepartmentId(id).toDepartmentId(depToMove).build());

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

}
