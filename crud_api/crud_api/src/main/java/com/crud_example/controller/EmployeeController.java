package com.crud_example.controller;

import com.crud_example.dto.LoginRequestDto;
import com.crud_example.model.Employee;
import com.crud_example.Service.EmployeeService;
import com.crud_example.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")

public class EmployeeController {

    private final EmployeeService employeeService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private JwtService jwtService;

    public EmployeeController(EmployeeService employeeService, AuthenticationManager authenticationManager, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.employeeService = employeeService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    // SAVE
    @PostMapping("/save")
    public String saveEmployee(@RequestBody Employee employee) {


        System.out.println(employee);
        employee.setMobile(passwordEncoder.encode(employee.getMobile()));

        employeeService.save(employee);
        return "Employee saved successfully";
    }

//    -----------------------------------------------------------------------------

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDto request) {
        System.out.println(request);
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getEmail(),
                                request.getMobile()
                        )
                );

        if (authentication.isAuthenticated()) {
            System.out.println("comletcoddd");

            return jwtService.generateToken(request.getEmail());
        }

        throw new RuntimeException("Invalid login");
    }




















    //-------------------------------------------------------------------------------------------
    // FIND ALL
    @GetMapping("/start")
    public String start() {

        return "its started  successfully";
    }

    @GetMapping("/all")
    public List<Employee> getAllEmployees() {

        System.out.println("all is don");
        return employeeService.findall();
    }


    // FIND BY ID
    @GetMapping("/{id}")
    public Optional<Employee> getEmployeeById(@PathVariable int id) {



return   employeeService.findbyid(id);

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
