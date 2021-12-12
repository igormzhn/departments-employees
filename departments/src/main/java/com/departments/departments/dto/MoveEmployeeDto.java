package com.departments.departments.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MoveEmployeeDto {

    private int fromDepartmentId;
    private int toDepartmentId;

}
