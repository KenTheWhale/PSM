package com.team5.psm.services;

import com.team5.psm.models.request_models.UpdateProfileRequest;
import org.springframework.ui.Model;

public interface UserService {
    String getUserProfile(Model model);
    String updateProfile(UpdateProfileRequest request, Model model);
}
