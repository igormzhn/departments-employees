package com.departments.departments.repository;

import com.departments.departments.model.Departments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface DepartmentRepository extends JpaRepository<Departments, Integer> {
}
