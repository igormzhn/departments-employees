package com.employees.employeesservice.model;

import lombok.*;

import javax.persistence.*;


@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class MyUser {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_users_seq_generator")
    @SequenceGenerator(name = "id_users_seq_generator",
            sequenceName = "users_id_seq",
            initialValue = 1,
            allocationSize = 1
    )
    private Integer id;
    private String username;
    private String password;
    private String authority;

}
