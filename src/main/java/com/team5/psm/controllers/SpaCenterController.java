package com.team5.psm.controllers;

import com.team5.psm.consts.FooterHTML;
import com.team5.psm.models.request_models.UpdateCenterRequest;
import com.team5.psm.services.SpaCenterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
@RequiredArgsConstructor
public class SpaCenterController {

    private final SpaCenterService spaCenterService;

    @PutMapping
    public String updateSpaCenter(UpdateCenterRequest request, Model model) {
        FooterHTML.setFooter(model);
        return spaCenterService.updateSpaCenter(request, model);
    }
}
