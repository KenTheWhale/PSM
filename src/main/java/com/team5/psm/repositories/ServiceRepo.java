package com.team5.psm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team5.psm.models.entity_models.Services;

public interface ServiceRepo extends JpaRepository<Services, Long> {

	List<Services> findTop3ByOrderByRatingDesc();
	
}
