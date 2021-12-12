package com.employees.employeesservice.dto;

import lombok.Data;

@Data
public class MoveDepartmentDto {

    private int fromDepartmentId;
    private int toDepartmentId;
}
