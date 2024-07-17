package com.team5.psm.service_implementors;

import com.team5.psm.models.entity_models.Account;
import com.team5.psm.models.entity_models.User;
import com.team5.psm.models.request_models.UpdateProfileRequest;
import com.team5.psm.repositories.UserRepo;
import com.team5.psm.services.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends BaseServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Override
    public String getUserProfile(Model model) {
        User user = getUserFromSession(model);
        if (user == null) {
            return model.containsAttribute("error") ? "login" : "home";
        }
        model.addAttribute("user", user);
        return "profile";
    }

    @Override
    public String updateProfile(UpdateProfileRequest request, Model model) {
        String name = request.getName();
        String phone = request.getPhone();
        String address = request.getAddress();
        LocalDate dob = request.getDob();

        if (!checkIfStringIsValid(name)
                || !checkIfStringIsValid(phone)
                || !checkIfStringIsValid(address)
                || dob == null) {
            model.addAttribute("error", "All fields are required.");
            return "profile";
        }

        User user = getUserFromSession(model);
        if (user == null) {
            return model.containsAttribute("error") ? "login" : "home";
        }

        user.setName(name);
        user.setPhone(phone);
        user.setAddress(address);
        user.setDob(dob);
        userRepo.save(user);

        model.addAttribute("user", user);
        return "profile";
    }

    private User getUserFromSession(Model model) {
        Account account = (Account) model.getAttribute("account");
        if (account == null) {
            model.addAttribute("error", "Please login to view your profile.");
            return null;
        }

        User user = account.getUser();
        if (user == null) {
            model.addAttribute("error", "User associated with this account not found.");
            return null;
        }
        return user;
    }
}