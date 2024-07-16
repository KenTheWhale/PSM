package com.team5.psm.controllers;

import com.team5.psm.services.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.team5.psm.consts.FooterHTML;
import com.team5.psm.requests.LoginRequest;
import com.team5.psm.requests.RegisterRequest;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CustomerController {

	private final IAccountService accountService;

	@Autowired
	public CustomerController(IAccountService accountService) {
		this.accountService = accountService;
	}

	@GetMapping("/login")
	public String login() {
		return "Login";
	}

	@PostMapping("/login")
	public String login(LoginRequest request, Model model) {
		FooterHTML.setFooter(model);
		return accountService.login(request.getEmail(), request.getPassword(), model);
	}

	@GetMapping("/register")
	public String loadRegister(RegisterRequest request, Model model) {
		FooterHTML.setFooter(model);
		return "register";
		// return accountService.login(request) here.
		// if process fail then return login page with error message variable named "error"
		// if success, then return home page with the account
	}
	
	@GetMapping("/profile")
	public String loadProfile(Model model) {
		FooterHTML.setFooter(model);
		return "profile";
	}
	
}
