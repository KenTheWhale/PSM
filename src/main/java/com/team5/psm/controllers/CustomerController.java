package com.team5.psm.controllers;

import com.team5.psm.models.request_models.ForgetPasswordRequest;
import com.team5.psm.models.request_models.PaymentRequest;
import com.team5.psm.services.AccountService;
import com.team5.psm.services.UserService;


import com.team5.psm.services.PaymentService;

import jakarta.servlet.http.HttpSession;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;



import com.team5.psm.consts.FooterHTML;
import com.team5.psm.models.entity_models.Account;
import com.team5.psm.models.entity_models.User;

import com.team5.psm.models.request_models.LoginRequest;
import com.team5.psm.models.request_models.RegisterRequest;
import com.team5.psm.models.request_models.UpdateProfileRequest;
import com.team5.psm.models.request_models.ViewProfileUserRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
public class CustomerController {

	private final AccountService accountService;

	private final PaymentService paymentService;
	private final UserService userService;
	
	@PostMapping("/login")
	public String login(LoginRequest request, Model model, HttpSession session) {
		return accountService.login(request, model, session);
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

	
	@GetMapping("/profile")
	public String loadProfile(Model model, HttpSession session) {
	    FooterHTML.setFooter(model);
	    
	    Account account = (Account) session.getAttribute("account");
	    if (account == null) {
	        model.addAttribute("error", "User not logged in");
	        return "login";
	    }

	    ViewProfileUserRequest request = new ViewProfileUserRequest();
	    request.setId(account.getId());
	    User user = userService.getUserbyAccountId(account.getId());
	    model.addAttribute("account", account);
	    model.addAttribute("user", user);
	    return userService.ViewProfileUser(request, model);
	}

	
	@PostMapping("/profile")
    public String updateProfile(UpdateProfileRequest request, Model model, HttpSession session) {
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            model.addAttribute("error", "User not logged in");
            return "login";
        }

        Long userId = account.getUser().getId();


       return userService.updateProfile(request, model, userId);
    }

	

}
