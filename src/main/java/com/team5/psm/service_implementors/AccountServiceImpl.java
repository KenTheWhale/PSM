package com.team5.psm.service_implementors;

import com.team5.psm.consts.Role;
import com.team5.psm.models.entity_models.Account;
import com.team5.psm.models.entity_models.User;
import com.team5.psm.models.request_models.ChangePasswordRequest;
import com.team5.psm.models.request_models.LoginRequest;
import com.team5.psm.models.request_models.RegisterRequest;
import com.team5.psm.repositories.UserRepo;
import com.team5.psm.services.AccountService;

import lombok.RequiredArgsConstructor;

import org.springframework.ui.Model;

import com.team5.psm.repositories.AccountRepo;

import java.time.LocalDate;

@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

	private final AccountRepo accountRepo;
	private final UserRepo userRepo;

	@Override
	public String login(LoginRequest request, Model model) {
		String email = request.getEmail();
		String password = request.getPassword();

		if (!checkIfStringIsValid(email) || !checkIfStringIsValid(password)) {
			model.addAttribute("error", "Login information is required.");
			return "login";
		}

		Account account = accountRepo.findByEmailAndPassword(email, password).orElse(null);

		if (account == null) {
			model.addAttribute("error", "Email or password incorrect.");
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
		String phone = request.getPhone();
		String address = request.getAddress();
		LocalDate dob = request.getDob();

		if (!checkIfStringIsValid(email)
				|| !checkIfStringIsValid(password)
				|| !checkIfStringIsValid(confirm)
				|| !checkIfStringIsValid(name)
				|| !checkIfStringIsValid(phone)
				|| !checkIfStringIsValid(address)
				|| dob == null) {
			model.addAttribute("error", "Please fill in all fields.");
			return "register";
		}

		// Check if password and confirm password match
		if (!password.equals(confirm)) {
			model.addAttribute("error", "Confirmed password does not match.");
			return "register";
		}
		
		// Check duplicated email
		if (accountRepo.existsByEmail(email)) {
			model.addAttribute("error", "Email already exists.");
			return "register";
		}

		Account account = Account.builder()
				.email(email)
				.password(password)
				.role(Role.USER)
				.build();

		accountRepo.save(account);
		
		User user = User.builder()
				.account(account)
				.name(name)
				.phone(phone)
				.address(address)
				.dob(dob)
				.build();

		userRepo.save(user);

		return "login";
	}

    @Override
    public String logout(Model model) {
        model.addAttribute("account", null);
        return "home";
    }

	@Override
	public String changePassword(ChangePasswordRequest request, Model model) {
		String oldPassword = request.getOldPassword();
		String newPassword = request.getNewPassword();

		if (!checkIfStringIsValid(oldPassword) || !checkIfStringIsValid(newPassword)) {
			model.addAttribute("error", "Please fill in all fields.");
			// Change this to the correct page later
			return "profile";
		}

		// Get account from session
		Account accountFromSession = (Account) model.getAttribute("account");
		if (accountFromSession == null) {
			model.addAttribute("error", "Please login first.");
			return "login";
		}

		String email = accountFromSession.getEmail();
		Account accountFromDb = accountRepo.findByEmailAndPassword(email, oldPassword).orElse(null);

		if (accountFromDb == null) {
			model.addAttribute("error", "Old password is incorrect.");
			return "profile";
		}

		accountFromDb.setPassword(newPassword);
		accountRepo.save(accountFromDb);

		return "profile";
	}

	private boolean checkIfStringIsValid(String input) {
		return input != null && !input.isEmpty();
	}
}
