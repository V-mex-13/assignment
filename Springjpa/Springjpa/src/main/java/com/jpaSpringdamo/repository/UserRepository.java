package com.jpaSpringdamo.repository;

import com.jpaSpringdamo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User,Integer> {
}
