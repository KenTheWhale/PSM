package com.team5.psm.services;


import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.team5.psm.models.entity_models.Services;

import com.team5.psm.models.entity_models.User;
import com.team5.psm.models.request_models.UpdateProfileRequest;
import com.team5.psm.models.request_models.ViewProfileUserRequest;

@Service
public interface UserService {
	
	User getUserbyAccountId(Long id);
	
	String ViewProfileUser(ViewProfileUserRequest request,Model model);
	
	String updateProfile(UpdateProfileRequest request, Model model, Long id);
  
  void loadHomePage(Model model);
	
}
