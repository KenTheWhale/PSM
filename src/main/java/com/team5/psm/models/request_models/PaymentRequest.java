package com.team5.psm.models.request_models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentRequest {
    // Card number should be 4242 4242 4242 4242 for testing purposes
    private String cardNumber;
    private String cardHolder;
    private LocalDate expiryDate;
    private String cvv;
    private String zipCode;
    // Amount should be in dollars
    private double amount;
}
