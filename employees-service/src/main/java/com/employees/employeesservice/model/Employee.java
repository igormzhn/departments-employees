package com.employees.employeesservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity
@Table(name = "employees")
@AllArgsConstructor
@NoArgsConstructor
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_employee_seq_generator")
    @SequenceGenerator(name = "id_employee_seq_generator",
            sequenceName = "employees_id_seq",
            initialValue = 1,
            allocationSize =1
    )
    private int id;
    private String name;
    private String lastname;
    private int departmentId;
    }
