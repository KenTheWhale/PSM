package com.team5.psm.service_implementors;

import com.team5.psm.consts.Role;
import com.team5.psm.models.entity_models.Account;
import com.team5.psm.models.entity_models.AccountStatus;
import com.team5.psm.models.entity_models.User;
import com.team5.psm.models.request_models.ForgetPasswordRequest;
import com.team5.psm.models.request_models.LoginRequest;
import com.team5.psm.models.request_models.RegisterRequest;
import com.team5.psm.repositories.AccountStatusRepo;
import com.team5.psm.repositories.UserRepo;
import com.team5.psm.services.AccountService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.team5.psm.repositories.AccountRepo;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl extends BaseServiceImpl implements AccountService {

	private final AccountRepo accountRepo;
	private final UserRepo userRepo;
	private final AccountStatusRepo accountStatusRepo;

	@Override
	public String login(LoginRequest request, Model model, HttpSession session) {
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

		if (account.getAccountStatus().getStatus().equals("banned")) {
			model.addAttribute("error", "Account is banned.");
			return "login";
		}

		model.addAttribute("account", account);
		session.setAttribute("account", account);
		return "redirect:/home";
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
    public String logout(Model model, HttpSession session) {
        model.addAttribute("account", null);
        session.invalidate();
        return "home";
    }

	@Override
	public String forgetPassword(ForgetPasswordRequest request, Model model) {
		String email = request.getEmail();
		LocalDate createdAt = request.getCreatedAt();
		String newPassword = request.getNewPassword();
		String newPasswordConfirm = request.getNewPasswordConfirm();

		if (!checkIfStringIsValid(email)
				|| !checkIfStringIsValid(newPassword)
				|| !checkIfStringIsValid(newPasswordConfirm)
				|| createdAt == null) {
			model.addAttribute("error", "Please fill in all fields.");
			return "login";
		}

		Account account = accountRepo.findByEmailAndCreatedAt(email, createdAt).orElse(null);

		if (account == null) {
			model.addAttribute("error", "Account not found.");
			return "login";
		}

		if (!newPassword.equals(newPasswordConfirm)) {
			model.addAttribute("error", "Confirmed password does not match.");
			return "login";
		}

		account.setPassword(newPassword);
		accountRepo.save(account);

		return "login";
	}

	private String changeAccountStatus(Long accountId, String status, Model model) {
		Account account = accountRepo.findById(accountId).orElse(null);
		if (account == null) {
			model.addAttribute("error", "Account not found.");
			// Change this to the correct page later
			return "home";
		}

		AccountStatus accountStatus = accountStatusRepo.findByStatus(status).orElse(null);
		if (accountStatus == null) {
			model.addAttribute("error", "Account status not found.");
			// Change this to the correct page later
			return "home";
		}

		account.setAccountStatus(accountStatus);
		accountRepo.save(account);

		// Change this to the correct page later
		return "home";
	}

	@Override
	public String banAccount(Long accountId, Model model) {
		return changeAccountStatus(accountId, AccountStatus.INACTIVE, model);
	}

	@Override
	public String unbanAccount(Long accountId, Model model) {
		return changeAccountStatus(accountId, AccountStatus.ACTIVE, model);
	}
}
