package com.team5.psm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.team5.psm.requests.LoginRequest;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CustomerController {

//	private final AccountService accountService;

	@GetMapping("/")
	public String loadLogin() {
		return "Login";
	}

	@PostMapping("/login")
	public String login(LoginRequest request) {
		return "";
		// return accountService.login(request) here.
		// if process fail then return login page with error message variable named "error"
		// if success, then return home page with the account
	}

}
