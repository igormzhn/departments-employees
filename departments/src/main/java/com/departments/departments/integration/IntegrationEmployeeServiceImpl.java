package com.departments.departments.integration;

import com.departments.departments.dto.MoveEmployeeDto;
import com.departments.departments.integration.IntegrationEmployeeService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public class IntegrationEmployeeServiceImpl implements IntegrationEmployeeService {
    private final RestTemplate employeeRestTemplate;

    public IntegrationEmployeeServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        employeeRestTemplate = restTemplateBuilder.build();
        employeeRestTemplate.getInterceptors().add(new ClientHttpRequestInterceptor() {
            @Override
            public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
                String auth = "user" + ":" + "user";
                byte[] encodeAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.US_ASCII));
                request.getHeaders().add("Authorization", "Basic " + new String(encodeAuth));
                return execution.execute(request, body);
            }
        });
    }



    @Override
    public void moveEmployee(MoveEmployeeDto moveEmployee){
        ResponseEntity<MoveEmployeeDto> res = employeeRestTemplate.postForEntity("http://localhost:8080/employees/move", moveEmployee, MoveEmployeeDto.class);
        if(!res.getStatusCode().equals(HttpStatus.OK)) {
            throw new IllegalArgumentException();       // сделать нормальное исключение
        }

    }
}
