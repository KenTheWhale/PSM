package com.team5.psm.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.team5.psm.models.entity_models.Services;


@Service
public interface UserService {

	void loadHomePage(Model model);
	
}
