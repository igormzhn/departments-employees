package com.departments.departments.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.checkerframework.checker.units.qual.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter @Setter
@Entity
@Table(name = "departments")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Departments {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "department_id_generator")
    @SequenceGenerator(name = "department_id_generator",
    sequenceName = "department_id",
    initialValue = 1,
    allocationSize = 1
    )
    private int id;
    @NotBlank(message = "Title can't be empty")
    @Size(min = 1, max = 64)
    private String title;
}
