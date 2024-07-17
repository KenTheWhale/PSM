package com.team5.psm.services;

import com.team5.psm.models.request_models.AddServiceRequest;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public interface ServicesService {
    String addService(AddServiceRequest request, Model model);

}
