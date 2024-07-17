package com.team5.psm.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.team5.psm.models.entity_models.Services;


@Service
public interface UserService {

	List<Services> loadTop3Service();
	
}
