package com.departments.departments.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ConfigurationProperties(prefix = "employees.service")
public class IntegrationConfigEmployee {

    @Bean
    //@Qualifier("employeeRestTemplate") // не нужна потому что бин создаться по имени метода
    public RestTemplate employeeRestTemplate() {
            return new  RestTemplate();
    }
}
