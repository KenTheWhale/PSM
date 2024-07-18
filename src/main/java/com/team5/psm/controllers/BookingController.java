package com.team5.psm.controllers;

import com.team5.psm.consts.FooterHTML;
import com.team5.psm.models.request_models.CancelBookingRequest;
import com.team5.psm.models.request_models.CreateBookingRequest;
import com.team5.psm.models.request_models.FeedBackRequest;
import com.team5.psm.services.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("/create-booking")
    public String createBooking(CreateBookingRequest request, Model model) {
        FooterHTML.setFooter(model);
        return bookingService.createBooking(request, model);
    }

    @PostMapping("/cancelBooking")
    public String createBooking(CancelBookingRequest request, Model model) {
        FooterHTML.setFooter(model);
        return bookingService.cancelBooking(request, model);
    }
    @PostMapping("/makeFeedBack")
    public String makeFeedBack(@RequestBody FeedBackRequest request, Model model) {
        FooterHTML.setFooter(model);
        System.out.println(request.getBookingId());
        System.out.println(request.getContent());
        return bookingService.makeFeedBack(request, model);
    }

}
