package com.employees.employeesservice.service;

import com.employees.employeesservice.model.Employee;
import com.employees.employeesservice.repository.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

//@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Captor
    ArgumentCaptor<Integer> idCaptor;

    @Autowired
    @InjectMocks
    private EmployeeServiceImpl employeeService;
    private Employee employee1;
    private Employee employee2;
    List<Employee> employeeList;

    @BeforeEach
    public void setUp() {
        employeeList = new ArrayList<>();
        employee1 = new Employee(1, "name", "lastname", 20);
        employee2 = new Employee(2, "name1", "lastname1", 200);
        employeeList.add(employee1);
        employeeList.add(employee2);
    }

    @AfterEach
    public void tearDown() {
        employee1 = employee2 = null;
        employeeList = null;
    }

    @Test
    public void findAllEmployees(){
        employeeRepository.save(employee1);
        when(employeeRepository.findAll()).thenReturn(employeeList);
        List<Employee> employeeList1 = employeeService.readAll();
        assertEquals(employeeList1,employeeList);
        verify(employeeRepository,times(1)).save(employee1);
        verify(employeeRepository,times(1)).findAll();
    }

    @Test
    public void readEmployeeById() {
        when(employeeRepository.getById(1)).thenReturn(employee1);
                assertThat(employeeService.read(employee1.getId())).isEqualTo(employee1);
    }

    @Test
    public void createEmployeeTest() {

        when(employeeRepository.save(any(Employee.class))).thenReturn(employee1);

        Employee savedEmployee = employeeRepository.save(employee1);
        assertThat(savedEmployee.getId()).isNotNull();
    }


    @Test
    public void deleteEmployee(){
        when(employeeService.delete(employee1.getId())).thenReturn(true);

        employeeService.delete(employee1.getId());
        verify(employeeRepository, times(1)).deleteById(idCaptor.capture());

        assertEquals(employee1.getId(), idCaptor.getValue());
    }

}