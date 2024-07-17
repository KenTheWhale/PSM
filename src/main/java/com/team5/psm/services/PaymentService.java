package com.team5.psm.services;

import com.team5.psm.models.request_models.PaymentRequest;
import org.springframework.ui.Model;



public interface PaymentService {
    String checkout(PaymentRequest request, Model model);
}
