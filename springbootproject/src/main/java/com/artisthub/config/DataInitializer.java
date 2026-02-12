package com.artisthub.config;

import com.artisthub.entity.User;
import com.artisthub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (!userRepository.existsByEmail("admin@artisthub.com")) {
            User admin = new User();
            admin.setName("Super Admin");
            admin.setEmail("admin@artisthub.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setPhone("1234567890");
            admin.setRole(User.Role.ADMIN);
            admin.setActive(true);
            
            userRepository.save(admin);
            System.out.println("Default Admin User created: admin@artisthub.com / admin123");
        }
    }
}
