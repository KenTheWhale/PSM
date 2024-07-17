package com.team5.psm.service_implementors;

import com.team5.psm.models.entity_models.*;
import com.team5.psm.models.request_models.CreateBookingRequest;
import com.team5.psm.repositories.*;
import com.team5.psm.services.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepo bookingRepo;
    private final DraftBookingRepo draftBookingRepo;

    @Override
    public String createBooking(CreateBookingRequest request, Model model) {
        DraftBooking draftBooking = draftBookingRepo.findById(request.getDraftBookingId()).orElse(null);
        Booking booking = Booking.builder()
                .pet(draftBooking.getPet())
                .service(draftBooking.getService())
                .createDate(LocalDate.now())
                .price(draftBooking.getPrice())
                .build();

        draftBookingRepo.delete(draftBooking);

        model.addAttribute("msg", "Check in success");
        return "home";
    }
}
