package com.team5.psm.services;

import com.team5.psm.consts.ERoleEnum;
import com.team5.psm.pojo.Role;
import com.team5.psm.repositories.IRoleRepository;
import com.team5.psm.requests.LoginRequest;
import com.team5.psm.requests.RegisterRequest;
import org.springframework.ui.Model;
import com.team5.psm.pojo.Account;
import com.team5.psm.repositories.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements IAccountService {

    private final IAccountRepository accountRepository;
    private final IRoleRepository roleRepository;

    @Autowired
    public AccountService(IAccountRepository accountRepository,
                          IRoleRepository roleRepository) {
        this.accountRepository = accountRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public String login(LoginRequest request, Model model) {
        String email = request.getEmail();
        String password = request.getPassword();

        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            model.addAttribute("error", "Login failed. Please check your email and password.");
            return "login";
        }

        Account account = accountRepository.findByEmailAndPassword(email, password).orElse(null);

        if (account == null) {
            model.addAttribute("error", "Login failed. Please check your email and password.");
            return "login";
        } else {
            model.addAttribute("account", account);
            return "home";
        }
    }

    @Override
    public String register(RegisterRequest request, Model model) {
        String email = request.getEmail();
        String password = request.getPassword();
        String confirm = request.getConfirmPassword();
        String name = request.getName();

        if (email == null ||
            email.isEmpty() ||
            password == null ||
            password.isEmpty() ||
            confirm == null ||
            confirm.isEmpty() ||
            name == null ||
            name.isEmpty()) {
            model.addAttribute("error", "Register failed. Please fill in all fields.");
            return "register";
        }

        if (!password.equals(confirm)) {
            model.addAttribute("error", "Register failed. Passwords do not match.");
            return "register";
        }

        Account account = new Account();
        account.setEmail(email);
        account.setPassword(password);
        Role role = roleRepository.findByName(ERoleEnum.ROLE_USER).orElse(null);
        if (role == null) {
            model.addAttribute("error", "Register failed. Role not found.");
            return "register";
        }
        account.setRole(role);

        // Create new customer entity later when the pojo and repository are available

        accountRepository.save(account);
        return "login";
    }
}
