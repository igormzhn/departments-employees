package com.employees.employeesservice.integration;

import com.employees.employeesservice.dto.Departments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

@Service
public class IntegrationDepartmentsServiceImpl implements IntegrationDepartmentService{
    private final RestTemplate departmentRestTemplate;

    @Autowired
    public IntegrationDepartmentsServiceImpl(RestTemplateBuilder restTemplateBuilder){
        departmentRestTemplate = restTemplateBuilder.build();
    }

    HttpHeaders createHeaders(String username, String password){
        return new HttpHeaders(){{
            final String basicAuth = HttpHeaders.encodeBasicAuth(username, password, StandardCharsets.US_ASCII);
            setBasicAuth(basicAuth);
        }};
    }

    @Override
    public Boolean ifDepartmentsExists(Integer departmentsId) {
           ResponseEntity<Departments> res = departmentRestTemplate.exchange("http://192.168.1.6:8081/v1/departments/" + departmentsId, HttpMethod.GET,
                    new HttpEntity<Void>(createHeaders("user", "user")), Departments.class);
            if (res.getStatusCode().equals(HttpStatus.OK)) {
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
    }
}
