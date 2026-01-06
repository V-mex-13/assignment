package com.crud_example.controller;

import com.crud_example.model.Employee;
import com.crud_example.Service.EmployeeService;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // SAVE
    @PostMapping("/save")
    public String saveEmployee(@RequestBody Employee employee) {
        employeeService.save(employee);
        return "Employee saved successfully";
    }

    // FIND ALL
    @GetMapping("/start")
    public String start() {

        return "its started  successfully";
    }

    @GetMapping("/all")
    public String getAllEmployees() {
        employeeService.findall();
        return "All employees fetched successfully";
    }

    // FIND BY ID
    @GetMapping("/{id}")
    public String getEmployeeById(@PathVariable int id) {
        employeeService.findbyid(id);
        return "Employee fetched successfully with id: " + id;
    }

    // UPDATE
    @PutMapping("/update")
    public String updateEmployee(@RequestBody Employee employee) {
        employeeService.update(employee);
        return "Employee updated successfully";
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable int id) {

employeeService.delete(id);
        return "Employee deleted successfully";
    }
}
