package com.team5.psm.services;

import org.springframework.ui.Model;

import org.springframework.stereotype.Service;

@Service
public interface IAccountService {
    String login(String email, String password, Model model);
}
