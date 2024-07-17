package com.team5.psm.controllers;

import com.team5.psm.models.request_models.ForgetPasswordRequest;
import com.team5.psm.models.request_models.PaymentRequest;
import com.team5.psm.services.AccountService;

import com.team5.psm.services.PaymentService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.team5.psm.models.request_models.LoginRequest;
import com.team5.psm.models.request_models.RegisterRequest;

import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
public class CustomerController {

	private final AccountService accountService;
	private final PaymentService paymentService;

	@PostMapping("/login")
	public String login(LoginRequest request, Model model) {
		return accountService.login(request, model);
	}

	@PostMapping("/register")
	public String register(RegisterRequest request, Model model) {
		return accountService.register(request, model);
	}

	@PostMapping("/logout")
	public String logout(Model model) {
		return accountService.logout(model);
	}

	@PostMapping("checkout")
	public String checkout(PaymentRequest request, Model model) {
		// Do something with this url
		String receiptUrl = paymentService.checkout(request, model);

		return "home";
	}

	@PostMapping("forget-password")
	public String forgetPassword(ForgetPasswordRequest request, Model model) {
		return accountService.forgetPassword(request, model);
	}
}
