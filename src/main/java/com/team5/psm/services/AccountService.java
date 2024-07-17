package com.team5.psm.services;

import org.springframework.ui.Model;

import com.team5.psm.models.request_models.LoginRequest;
import com.team5.psm.models.request_models.RegisterRequest;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

@Service
public interface AccountService {
    String login(LoginRequest request, Model model, HttpSession session);
    String register(RegisterRequest request, Model model);
}
