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
