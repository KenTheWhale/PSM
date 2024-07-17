package com.team5.psm.service_implementors;

import com.team5.psm.models.entity_models.Account;
import com.team5.psm.models.entity_models.User;
import com.team5.psm.repositories.UserRepo;
import com.team5.psm.services.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.ui.Model;

@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Override
    public String getUserProfile(Model model) {
        // Get the account from session
        Account account = (Account) model.getAttribute("account");

        if (account == null) {
            model.addAttribute("error", "Please login to view your profile.");
            return "login";
        }

        // Get the user from the account
        User user = account.getUser();
        if (user == null) {
            model.addAttribute("error", "User associated with this account not found.");
            return "home";
        }
        model.addAttribute("user", user);
        return "profile";
    }
}
