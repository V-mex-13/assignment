package com.crud_example.Service;

import com.crud_example.model.Employee;
import com.crud_example.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    EmployeeRepository employeeRepository;
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }





    public Employee save(Employee e){

        return employeeRepository.save(e);


    }
    //ALl employees
    public List<Employee> findall(){
        return employeeRepository.findAll();
    }



    public Optional<Employee> findbyid(int id){
        return employeeRepository.findById(id);
    }



    public Employee update(Employee e){
        return employeeRepository.save(e);
    }


   public void delete(int id){


        employeeRepository.deleteById(id);

    }


}