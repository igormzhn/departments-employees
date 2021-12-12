package com.departments.departments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // сканироать пакеты для поиска и создания бинов
public class DepartmentsApplication {

    public static void main(String[] args) {
        SpringApplication.run(DepartmentsApplication.class, args);
    }

}
