package com.team5.psm.services;


import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.team5.psm.models.entity_models.User;
import com.team5.psm.models.request_models.UpdateProfileRequest;
import com.team5.psm.models.request_models.ViewBookingHistoryRequest;
import com.team5.psm.models.request_models.ViewProfileUserRequest;

@Service
public interface UserService {
	
	User getUserbyAccountId(Long id);
	
	String ViewProfileUser(ViewProfileUserRequest request,Model model);
	
	String updateProfile(UpdateProfileRequest request, Model model, Long id);
  
	void loadHomePage(Model model);
	
	void loadServicePage(Model model);
	
	void loadDetailService(Long id, Model model);
 
	String ViewBookingHistory(ViewBookingHistoryRequest request, Model model);
	
}
