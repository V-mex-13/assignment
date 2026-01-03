package com.jpaSpringdamo;

import com.jpaSpringdamo.entity.User;
import com.jpaSpringdamo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringjpaApplication {

	@Autowired
	private UserRepository userRepository;

	void sum(){

		User user =new User();

		user.setEmail("meet vaghela");
		user.setEmail("meet@meet.com");
		user.setAddress("earth");
		userRepository.save(user);

	}


	public static void main(String[] args) {





		ConfigurableApplicationContext conainer =SpringApplication.run(SpringjpaApplication.class, args);

conainer.getBean(SpringjpaApplication.class).sum();


	}


}
