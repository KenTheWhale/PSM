package com.team5.psm.service_implementors;

import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;


import com.team5.psm.models.entity_models.User;
import com.team5.psm.models.request_models.UpdateProfileRequest;
import com.team5.psm.models.request_models.ViewProfileUserRequest;

import com.team5.psm.repositories.PetRepo;
import com.team5.psm.repositories.ServiceRepo;

import com.team5.psm.repositories.UserRepo;
import com.team5.psm.services.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

	private final UserRepo userRepo;
  private final ServiceRepo serviceRepo;
	private final PetRepo petRepo;
	
	@Override
	public String ViewProfileUser(ViewProfileUserRequest request, Model model) {
	    User user = userRepo.findByAccount_Id(request.getId());
	    
	    if(user == null) {
	        model.addAttribute("error", "User not found");
	        return "profile";
	    }
	    model.addAttribute("user", user);
	    return "profile";
	}

	@Override
	public User getUserbyAccountId(Long id) {
		// TODO Auto-generated method stub
		return userRepo.findByAccount_Id(id);
	}

	@Override
	public String updateProfile(UpdateProfileRequest request, Model model, Long id) {
	    String name = request.getName();
	    String phone = request.getPhone();
	    LocalDate dob = request.getDob();
	    String address = request.getAddress();
	    
	    User user = userRepo.findById(id).orElse(null);
	    
	    if (user == null) {
	        model.addAttribute("error", "User not found");
	        return "redirect:/profile";
	    }

	    
	    if (!phone.matches("\\d+")) {
	        model.addAttribute("error", "Phone must be a number");
	        return "redirect:/profile"; 
	    }

	    
	    if (dob == null) {
	        model.addAttribute("error", "Date of birth must be a valid date");
	        return "redirect:/profile";
	    }

	    
	    user.setName(name);
	    user.setPhone(phone);
	    user.setDob(dob);
	    user.setAddress(address);
	    
	    
	    userRepo.save(user);

	    model.addAttribute("success", "Profile updated successfully");
	    return "redirect:/profile";
	}

	
	@Override
	public void loadHomePage(Model model) {
		serviceRepo.findTop3ByOrderByRatingDesc();
		model.addAttribute("top3Services", serviceRepo.findTop3ByOrderByRatingDesc());
		model.addAttribute("userCount", String.valueOf(userRepo.count()));
		model.addAttribute("serviceCount", serviceRepo.count());
		model.addAttribute("petCount", petRepo.count());
	}		
	
}
