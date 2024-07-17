package com.team5.psm.service_implementors;

import com.team5.psm.models.entity_models.Services;
import com.team5.psm.models.entity_models.SpaCenter;
import com.team5.psm.models.request_models.AddServiceRequest;
import com.team5.psm.repositories.ServicesRepo;
import com.team5.psm.repositories.SpaCenterRepo;
import com.team5.psm.services.ServicesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
@RequiredArgsConstructor
public class ServicesServiceImpl implements ServicesService {

    private ServicesRepo servicesRepo;
    private SpaCenterRepo spaCenterRepo;

    @Override
    public String addService(AddServiceRequest request, Model model) {
        SpaCenter spaCenter = spaCenterRepo.findById(request.getSpaId()).orElse(null);
        Services service = Services.builder()
                .spaCenter(spaCenter)
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .build();

        servicesRepo.save(service);
        return "home";
    }


}
