package com.departments.departments.controller;

import com.departments.departments.model.Departments;
import com.departments.departments.service.DepartmentService;
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

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = DepartmentController.class)
@WithMockUser(username = "user", password = "user", roles = "user")
public class DepartmentControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private DepartmentService departmentService;

    @Test
    public void getAllDeprtments() throws Exception{
        when(departmentService.readAll()).thenReturn(getDepartmentsList());
        mvc.perform( MockMvcRequestBuilders
                        .get("/v1/departments")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[*].id").exists())
                .andExpect(jsonPath("$.[*].id").isNotEmpty());
        verify(departmentService, times(1)).readAll();
    }

    @Test
    public void getDepartmentsById() throws Exception{
        when(departmentService.read(1)).thenReturn(getDepartmentsList().get(0));
        mvc.perform( MockMvcRequestBuilders
                        .get("/v1/departments/{id}", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("name"));
        verify(departmentService, times(1)).read(1);
    }


    private List<Departments> getDepartmentsList(){
        List<Departments> departmentsList = new ArrayList<>();
        departmentsList.add(new Departments(1, "name"));
        return departmentsList;
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
