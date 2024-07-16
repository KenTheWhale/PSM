package com.team5.psm.service_implementors;

import com.team5.psm.consts.Role;
import com.team5.psm.models.entity_models.Account;
import com.team5.psm.models.request_models.LoginRequest;
import com.team5.psm.models.request_models.RegisterRequest;
import com.team5.psm.services.AccountService;

import lombok.RequiredArgsConstructor;

import org.springframework.ui.Model;

import com.team5.psm.repositories.AccountRepo;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

	private final AccountRepo accountRepository;

	@Override
	public String login(LoginRequest request, Model model) {
		String email = request.getEmail();
		String password = request.getPassword();

		if (!checkIfStringIsValid(email) || !checkIfStringIsValid(password)) {
			model.addAttribute("error", "Login information is required");
			return "login";
		}

		Account account = accountRepository.findByEmailAndPassword(email, password).orElse(null);

		if (account == null) {
			model.addAttribute("error", "Email or password inc");
			return "login";
		}

		model.addAttribute("account", account);
		return "home";
	}

	@Override
	public String register(RegisterRequest request, Model model) {
		String email = request.getEmail();
		String password = request.getPassword();
		String confirm = request.getConfirmPassword();
		String name = request.getName();

		if (!checkIfStringIsValid(email) || !checkIfStringIsValid(password) || !checkIfStringIsValid(confirm) || !checkIfStringIsValid(name)) {
			model.addAttribute("error", "Please fill in all fields.");
			return "register";
		}

		if (!password.equals(confirm)) {
			model.addAttribute("error", "Confirmed password does not matched.");
			return "register";
		}
		
		// Check duplicated email
		if (accountRepository.existsByEmail(email)) {
			model.addAttribute("error", "Email already exists.");
			return "register";
		}

		Account account = Account.builder()
				.email(email)
				.password(password)
				.role(Role.USER)
				.build();
		
		// Create new customer entity later when the pojo and repository are available

		accountRepository.save(account);
		return "login";
	}

	private boolean checkIfStringIsValid(String input) {
		return input != null && !input.isEmpty();
	}
}
