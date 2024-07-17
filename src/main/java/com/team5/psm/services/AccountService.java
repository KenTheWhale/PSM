package com.team5.psm.services;

import com.team5.psm.models.request_models.ForgetPasswordRequest;
import org.springframework.ui.Model;

import com.team5.psm.models.request_models.LoginRequest;
import com.team5.psm.models.request_models.RegisterRequest;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

@Service
public interface AccountService {
    String login(LoginRequest request, Model model, HttpSession session);
    String register(RegisterRequest request, Model model);
    String logout(Model model);
    String forgetPassword(ForgetPasswordRequest request, Model model);
    String banAccount(Long accountId, Model model);
    String unbanAccount(Long accountId, Model model);
}
