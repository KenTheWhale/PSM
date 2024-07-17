package com.team5.psm.service_implementors;

import java.util.List;

import org.springframework.stereotype.Service;

import com.team5.psm.models.entity_models.Services;
import com.team5.psm.repositories.ServiceRepo;
import com.team5.psm.services.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	
	private final ServiceRepo serviceRepo;
	
	@Override
	public List<Services> loadTop3Service() {
		return serviceRepo.findTop3ByOrderByRatingDesc();
	}
		
	
}
