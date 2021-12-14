package com.employees.employeesservice.controller;

import com.employees.employeesservice.model.Employee;
import com.employees.employeesservice.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = EmployeeController.class)
@WithMockUser(username = "user", password = "user", roles = "user")
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private EmployeeService employeeService;

    @Test
    public void getAllEmployees() throws Exception{
        when(employeeService.readAll()).thenReturn(getEmployeeList());
        mvc.perform( MockMvcRequestBuilders
                        .get("/employees")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[*].id").exists())
                .andExpect(jsonPath("$.[*].id").isNotEmpty());
        verify(employeeService, times(1)).readAll();
    }

    @Test
    public void getEmployeeById() throws Exception{
        when(employeeService.read(1)).thenReturn(getEmployeeList().get(0));
        mvc.perform( MockMvcRequestBuilders
                        .get("/employees/{id}", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("name"))
                .andExpect(jsonPath("$.lastname").value("lastname"))
                .andExpect(jsonPath("$.departmentId").value(1));
        verify(employeeService, times(1)).read(1);
    }

    @Test
    public void createEmployee() throws Exception{
        when(employeeService.create(getEmployeeList().get(0))).thenReturn(1);
        mvc.perform( MockMvcRequestBuilders
                        .post("/employees")
                        .content(asJsonString(getEmployeeList().get(0)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
        verify(employeeService, times(1)).create(eq(getEmployeeList().get(0)));
    }

    @Test
    public void deleteEmployee() throws Exception
    {
        when(employeeService.delete(1)).thenReturn(true);
        mvc.perform( MockMvcRequestBuilders.delete("/employees/{id}", 1) )
                .andExpect(status().isOk());
        verify(employeeService, times(1)).delete(1);
    }

    private List<Employee> getEmployeeList(){
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee(1, "name", "lastname", 1));
        return employeeList;
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
