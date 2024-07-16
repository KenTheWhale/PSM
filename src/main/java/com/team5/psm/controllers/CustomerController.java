package com.team5.psm.controllers;

import com.team5.psm.services.AccountService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.team5.psm.consts.FooterHTML;
import com.team5.psm.models.request_models.LoginRequest;
import com.team5.psm.models.request_models.RegisterRequest;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class CustomerController {

	private final AccountService accountService;

	@PostMapping("/login")
	public String login(LoginRequest request, Model model) {
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
