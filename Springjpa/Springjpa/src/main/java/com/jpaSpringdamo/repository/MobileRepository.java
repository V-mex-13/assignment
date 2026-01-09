package com.jpaSpringdamo.repository;

import com.jpaSpringdamo.entity.Mobile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MobileRepository  extends JpaRepository<Mobile,Integer> {
}
