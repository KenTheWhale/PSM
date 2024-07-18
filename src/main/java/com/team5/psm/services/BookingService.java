package com.team5.psm.services;

import com.team5.psm.models.request_models.CancelBookingRequest;
import com.team5.psm.models.request_models.CreateBookingRequest;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public interface BookingService {
    String createBooking(CreateBookingRequest request, Model model);
    String cancelBooking(CancelBookingRequest request, Model model);
}
