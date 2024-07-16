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
		return accountService.login(request, model);
	}

	@GetMapping("/register")
	public String loadRegister() {
		return "register";
	}

	@PostMapping("/register")
	public String register(RegisterRequest request, Model model) {
		FooterHTML.setFooter(model);
		return accountService.register(request, model);
	}
	
	@GetMapping("/profile")
	public String loadProfile(Model model) {
		FooterHTML.setFooter(model);
		return "profile";
	}
	
}
