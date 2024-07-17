package com.team5.psm.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team5.psm.models.entity_models.User;

public interface UserRepo extends JpaRepository<User, Long>{

	User findByAccount_Id(Long id);
	
	
}
