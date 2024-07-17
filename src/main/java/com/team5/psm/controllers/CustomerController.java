package com.team5.psm.controllers;

import com.team5.psm.models.request_models.PaymentRequest;
import com.team5.psm.models.request_models.UpdateProfileRequest;
import com.team5.psm.services.AccountService;

import com.team5.psm.services.PaymentService;
import com.team5.psm.services.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.team5.psm.consts.FooterHTML;
import com.team5.psm.models.request_models.LoginRequest;
import com.team5.psm.models.request_models.RegisterRequest;

import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
public class CustomerController {

	private final AccountService accountService;
	private final UserService userService;
	private final PaymentService paymentService;

	@PostMapping("/login")
	public String login(LoginRequest request, Model model) {
		PaymentRequest paymentRequest = new PaymentRequest();
		paymentRequest.setAmount(1000);
		paymentRequest.setCardNumber("4242424242424242");
		paymentRequest.setCvv("123");
		paymentRequest.setExpiryDate(LocalDate.of(2050, 1, 1));
		paymentRequest.setCardHolder("John Doe");
		paymentRequest.setZipCode("12345");

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

	@PostMapping("/update-profile")
	public String updateProfile(UpdateProfileRequest request, Model model) {
		return userService.updateProfile(request, model);
	}

	@PostMapping("checkout")
	public String checkout(PaymentRequest request, Model model) {
		// Do something with this url
		String receiptUrl = paymentService.checkout(request, model);

		return "home";
	}
}
