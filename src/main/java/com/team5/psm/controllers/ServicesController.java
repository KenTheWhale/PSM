package com.team5.psm.controllers;

import com.team5.psm.consts.FooterHTML;
import com.team5.psm.models.request_models.AddServiceRequest;
import com.team5.psm.models.request_models.UpdateServiceRequest;
import com.team5.psm.services.ServicesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
@RequiredArgsConstructor
public class ServicesController {

    private final ServicesService servicesService;

    @PostMapping("/add-service")
    public String addService(AddServiceRequest request, Model model) {
        FooterHTML.setFooter(model);
        return servicesService.addService(request, model);
    }

    @PutMapping("/update-service")
    public String updateService(UpdateServiceRequest request, Model model) {
        FooterHTML.setFooter(model);
        return servicesService.updateService(request, model);
    }

}
