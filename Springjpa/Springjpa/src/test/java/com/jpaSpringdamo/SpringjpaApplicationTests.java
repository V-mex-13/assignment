package com.jpaSpringdamo;

import com.jpaSpringdamo.entity.Man;
import com.jpaSpringdamo.entity.Mobile;
import com.jpaSpringdamo.repository.ManRepository;
import com.jpaSpringdamo.repository.MobileRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Rollback(false)

class SpringjpaApplicationTests {
	@Autowired
	ManRepository manRepository;

	@Autowired
	MobileRepository mobileRepository;
	@Test
	void test() {

		Mobile m1 = new Mobile();
		m1.setModel("iPhone17");
		m1.setPrice("100000");
		m1.setStorge("500");

		Mobile m2 = new Mobile();
		m2.setModel("iPhone17");
		m2.setPrice("100000");
		m2.setStorge("500");

		Man man = new Man();
		man.setName("tommy");

		man.getMobileList().add(m1);

		man.getMobileList().add(m2);

		manRepository.save(man); // 🔥 ONLY THIS IS NEEDED
	}


}
