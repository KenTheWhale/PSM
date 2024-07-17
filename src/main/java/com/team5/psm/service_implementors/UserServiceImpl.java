package com.team5.psm.service_implementors;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.team5.psm.models.entity_models.Services;
import com.team5.psm.repositories.PetRepo;
import com.team5.psm.repositories.ServiceRepo;
import com.team5.psm.repositories.UserRepo;
import com.team5.psm.services.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	
	private final ServiceRepo serviceRepo;
	private final UserRepo userRepo;
	private final PetRepo petRepo;
	
	
	@Override
	public void loadHomePage(Model model) {
		serviceRepo.findTop3ByOrderByRatingDesc();
		model.addAttribute("top3Services", serviceRepo.findTop3ByOrderByRatingDesc());
		model.addAttribute("userCount", String.valueOf(userRepo.count()));
		model.addAttribute("serviceCount", serviceRepo.count());
		model.addAttribute("petCount", petRepo.count());
	}		
	
}
