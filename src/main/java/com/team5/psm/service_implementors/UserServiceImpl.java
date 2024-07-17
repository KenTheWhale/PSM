package com.team5.psm.service_implementors;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.team5.psm.models.entity_models.User;
import com.team5.psm.models.request_models.ViewProfileUserRequest;
import com.team5.psm.repositories.AccountRepo;
import com.team5.psm.repositories.UserRepo;
import com.team5.psm.services.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	private final UserRepo userRepo;
	
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

}
