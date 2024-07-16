package com.team5.psm.services;

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
    public String login(String email, String password, Model model) {
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
