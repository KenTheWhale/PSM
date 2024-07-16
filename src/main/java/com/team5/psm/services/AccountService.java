package com.team5.psm.services;

import com.team5.psm.requests.LoginRequest;
import org.springframework.ui.Model;
import com.team5.psm.pojo.Account;
import com.team5.psm.repositories.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements IAccountService {

    private final IAccountRepository accountRepository;

    @Autowired
    public AccountService(IAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
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
}
