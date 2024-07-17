package com.team5.psm.controllers;

import com.team5.psm.consts.FooterHTML;
import com.team5.psm.models.request_models.AddServiceRequest;
import com.team5.psm.services.ServicesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ServicesController {

    private final ServicesService servicesService;

    @PostMapping("/add-service")
    public String addService(AddServiceRequest request, Model model) {
        FooterHTML.setFooter(model);
        return servicesService.addService(request, model);
    }

}
