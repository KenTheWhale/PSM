package com.team5.psm.services;

import com.team5.psm.requests.LoginRequest;
import org.springframework.ui.Model;

import org.springframework.stereotype.Service;

@Service
public interface IAccountService {
    String login(LoginRequest request, Model model);
}
