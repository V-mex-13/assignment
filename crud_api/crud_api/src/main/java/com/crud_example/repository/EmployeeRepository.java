package com.crud_example.repository;

import com.crud_example.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface EmployeeRepository extends JpaRepository<Employee,Integer> {


    Optional<Employee> findByEmail(String email);



}
