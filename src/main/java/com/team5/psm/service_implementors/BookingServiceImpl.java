package com.team5.psm.service_implementors;

import com.team5.psm.models.entity_models.*;
import com.team5.psm.models.request_models.CancelBookingRequest;
import com.team5.psm.models.request_models.CreateBookingRequest;
import com.team5.psm.models.request_models.FeedBackRequest;
import com.team5.psm.repositories.*;
import com.team5.psm.services.BookingService;

import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;


@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepo bookingRepo;
    private final DraftBookingRepo draftBookingRepo;
    private final BookingStatusRepo bookingStatusRepo;
    private final FeedbackRepo feedbackRepo;

    @Override
    public String createBooking(CreateBookingRequest request, Model model) {
        DraftBooking draftBooking = draftBookingRepo.findById(request.getDraftBookingId()).orElse(null);
        Booking booking = Booking.builder()
                .pet(draftBooking.getPet())
                .servicePlan(draftBooking.getServicePlan())
                .isFeedbacked(false)
                .createDate(LocalDate.now())
                .price(draftBooking.getPrice())
                .build();

        draftBookingRepo.delete(draftBooking);
        bookingRepo.save(booking);
        model.addAttribute("msg", "Check in successfully");
        return "home";
    }

    @Override
    public String cancelBooking(CancelBookingRequest request, Model model) {
        Long bookingId = request.getBookingId();
        
        BookingStatus cancelStatus = getBookingStatusCancel();
       
        
        Booking booking = bookingRepo.findById(bookingId).orElse(null);
        if (booking != null && isBookingStatusProcessing(booking)) {
            
            booking.setBookingStatus(cancelStatus);

            bookingRepo.save(booking);
        } else {
            model.addAttribute("error", "Booking not found");
            return "error";
        }

        return "redirect:/bookinghistory";
    }
    
    
    
    private BookingStatus getBookingStatusCancel() {
    	List<BookingStatus> bookingStatusList= bookingStatusRepo.findAll();
    	for (BookingStatus bookingStatus : bookingStatusList) {
    		if(bookingStatus.getId() == 4) {
    			return bookingStatus;
    		}
    	}
    	return null;
    }
    private boolean isBookingStatusProcessing(Booking booking) {
        return booking.getBookingStatus() != null && booking.getBookingStatus().getId() == 1;
    }

    @Override
    public String makeFeedBack(FeedBackRequest request, Model model) {
        Long bookingId = request.getBookingId();
        String content = request.getContent();

        Booking booking = bookingRepo.findById(bookingId).orElse(null);
        if (booking == null) {
            model.addAttribute("error", "Booking not found");
            return "error";
        }

        if (booking.isFeedbacked()) {
            model.addAttribute("error", "This booking has already been feedbacked");
            return "error";
        }

        Feedback feedback = Feedback.builder()
                .booking(booking)
                .content(content)
                .createDate(LocalDate.now())
                .booking(booking)
                .build();
        feedbackRepo.save(feedback);

        booking.setFeedbacked(true);
        bookingRepo.save(booking);

        model.addAttribute("msg", "Feedback submitted successfully");
        return "bookinghistory";
    }



}
