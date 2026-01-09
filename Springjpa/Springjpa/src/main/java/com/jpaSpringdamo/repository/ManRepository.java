package com.jpaSpringdamo.repository;

import com.jpaSpringdamo.entity.Man;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManRepository extends JpaRepository<Man,Integer>
{
}
