package com.crud_example.security;

import com.crud_example.repository.EmployeeRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class custamUserDetailService  implements UserDetailsService {

    EmployeeRepository employeeRepository;

    public custamUserDetailService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

     return     employeeRepository.findByEmail(username).orElseThrow();

    }
}
