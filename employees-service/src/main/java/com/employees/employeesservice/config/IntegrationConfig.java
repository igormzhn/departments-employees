package com.employees.employeesservice.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ConfigurationProperties(prefix = "departments.service")
@Slf4j
public class IntegrationConfig {
    //@Value("departments.service.host:localhost")
    private static String host = "";
    //System.out.println(host);
    //@Value("departments.service.protocol:https")
    private static String protocol = "";
    //@Value("departments.service.port:8080")
    private static String port = "";
    //@Value("departments.service.path:departments")
    private static String path;
    //public static final URI DEPARTMENTS_ENDPOINT =
     //        UriComponentsBuilder.newInstance().scheme(protocol).host(host).port(port).path(path).build().toUri();

   // @PostConstruct
    //public void init(){
    // log.info(DEPARTMENTS_ENDPOINT.toString());
   // }

    @Bean
    @Qualifier("departmentRestTemplate")
    public RestTemplate departmentRestTemplate(){
        return new RestTemplate();
    }


}

