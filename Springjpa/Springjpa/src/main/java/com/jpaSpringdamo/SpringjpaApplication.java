package com.jpaSpringdamo;

import com.jpaSpringdamo.entity.Laptop;
import com.jpaSpringdamo.entity.User;
import com.jpaSpringdamo.repository.LaptopRepository;
import com.jpaSpringdamo.repository.ManRepository;
import com.jpaSpringdamo.repository.MobileRepository;
import com.jpaSpringdamo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringjpaApplication {
//
//	@Autowired
//	private UserRepository userRepository;
//    @Autowired
//    private LaptopRepository laptopRepository;


	@Autowired
	ManRepository manRepository;

	@Autowired
	MobileRepository mobileRepository;


	void sum() {

		manRepository.findAll();
//		User user = new User();
//		user.setName("noone vaghela");
//		user.setEmail("noone@meet.com");
//		user.setAddress("earth");
//
//		Laptop laptop = new Laptop();
//		laptop.setName("hp1001");
//		laptop.setModel("2025new model");
//
//		// Set both sides of the relation
//		user.setLaptop(laptop);
//		laptop.setUser(user);
//
//		// Save only User — Laptop is saved automatically via Cascade
//		User savedUser = userRepository.save(user);
//
//		System.out.println("User ID: " + savedUser.getUserid());
	}





	public static void main(String[] args) {
		ConfigurableApplicationContext container = SpringApplication.run(SpringjpaApplication.class, args);
		container.getBean(SpringjpaApplication.class).sum();
	}
}
