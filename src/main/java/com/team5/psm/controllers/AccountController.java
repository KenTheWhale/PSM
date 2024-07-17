package com.team5.psm.controllers;

import com.team5.psm.services.AccountService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/ban-account")
    public String banAccount(Long accountId, Model model) {
        return accountService.banAccount(accountId, model);
    }

    @PostMapping("/unban-account")
    public String unbanAccount(Long accountId, Model model) {
        return accountService.unbanAccount(accountId, model);
    }
}
