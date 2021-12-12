package com.employees.employeesservice.controller;

import com.employees.employeesservice.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;


//@ExtendWith(SpringExtension.class)
//@WebMvcTest(controllers = EmployeeController.class)
public class EmployeeControllerTest {
    //@Autowired
    //private MockMvc mockMvc;
    //@Autowired
    private ObjectMapper objectMapper;
    //@MockBean
    private EmployeeService employeeService;
    //@Captor
    //ArgumentCaptor<Employee> employeeCaptor;

    }


        //when(employeeService.create(any())).thenReturn(1);
        //ObjectMapper mapper = new ObjectMapper();
        //mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        //ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        //String requestJson = ow.writeValueAsString(createEmployee());
        //mockMvc.perform(MockMvcRequestBuilders.post("/employees").with(SecurityMockMvcRequestPostProcessors.httpBasic("user", "user") ).contentType(APPLICATION_JSON_UTF8).content(requestJson)).andExpect(status().isCreated());
        //verify(employeeService).create(employeeCaptor.capture());
        //Employee res = employeeCaptor.getValue();
        //System.out.println(" ");
        //header(HttpHeaders.AUTHORIZATION,
        //                "Basic " + Base64Utils.encodeToString("user:user".getBytes()))



  /* private Employee createEmployee(){
        Employee employee = new Employee();
        employee.setDepartmentId(1);
        employee.setLastname("Ivanov");
        employee.setName("Ivan");
        return employee;
    }*/

