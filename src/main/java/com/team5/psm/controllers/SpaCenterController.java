package com.team5.psm.controllers;

import com.team5.psm.models.request_models.UpdateCenterProfileRequest;
import com.team5.psm.services.SpaCenterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class SpaCenterController {

    private final SpaCenterService centerService;

    @PostMapping("/update-center-profile")
    public String updateProfile(UpdateCenterProfileRequest request, Model model) {
        return centerService.updateCenterProfile(request, model);
    }
}
