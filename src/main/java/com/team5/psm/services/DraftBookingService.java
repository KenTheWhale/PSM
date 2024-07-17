package com.team5.psm.services;

import com.team5.psm.models.request_models.UpdateDraftBookingRequest;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.team5.psm.models.request_models.CreateDraftBookingRequest;

@Service
public interface DraftBookingService {
    String createDraftBooking(CreateDraftBookingRequest request, Model model);
    String updateDraftBooking(UpdateDraftBookingRequest request, Model model);

}
