package com.team5.psm.controllers;

import com.team5.psm.consts.FooterHTML;
import com.team5.psm.models.request_models.CreateDraftBookingRequest;
import com.team5.psm.models.request_models.DeleteDraftBookingRequest;
import com.team5.psm.models.request_models.UpdateDraftBookingRequest;
import com.team5.psm.services.DraftBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
@RequiredArgsConstructor
public class DraftBookingController {

    private final DraftBookingService draftBookingService;

    @PostMapping("/create-draft-booking")
    public String createDraftBooking(CreateDraftBookingRequest request, Model model) {
        FooterHTML.setFooter(model);
        return draftBookingService.createDraftBooking(request, model);
    }

    @PutMapping("/update-draft-booking")
    public String updateDraftBooking(UpdateDraftBookingRequest request, Model model) {
        FooterHTML.setFooter(model);
        return draftBookingService.updateDraftBooking(request, model);
    }

    @DeleteMapping("/delete-draft-booking")
    public String deleteDraftBooking(DeleteDraftBookingRequest request, Model model) {
        FooterHTML.setFooter(model);
        return draftBookingService.deleteDraftBooking(request, model);
    }

}
