package com.team5.psm.service_implementors;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.param.ChargeCreateParams;
import com.team5.psm.models.request_models.PaymentRequest;
import com.team5.psm.services.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl extends BaseServiceImpl implements PaymentService {
    private static final String STRIPE_SECRET_KEY = "sk_test_51PdZJHFeUg8puE8rrb7bYUdrq9XByd7kRV0kD1LeR6bfW63OSgTGONr98SiuGxBEQY2b2vGkM6iOcgaidzYcnsts00h9cnqYgV";

    @Override
    public String checkout(PaymentRequest request, Model model) {
        Stripe.apiKey = STRIPE_SECRET_KEY;

        // These are dummy values for testing purposes
        String cardNumber = request.getCardNumber();
        String cardHolder = request.getCardHolder();
        String cvv = request.getCvv();
        String zipCode = request.getZipCode();
        LocalDate expiryDate = request.getExpiryDate();

        // Amount in cents
        long amount = (long) (request.getAmount() * 100);

        if (!checkIfStringIsValid(cardNumber)
                || !checkIfStringIsValid(cardHolder)
                || !checkIfStringIsValid(cvv)
                || !checkIfStringIsValid(zipCode)
                || expiryDate == null
                || amount <= 0) {
            model.addAttribute("error", "Payment information is required.");
            return "checkout";
        }

        try {
            ChargeCreateParams params =
                    ChargeCreateParams.builder()
                            .setAmount(amount)
                            .setCurrency("usd")
                            .setSource("tok_visa")
                            .build();

            Charge charge = Charge.create(params);
            return charge.getReceiptUrl();
        } catch (StripeException e) {
            return "checkout";
        }
    }
}
