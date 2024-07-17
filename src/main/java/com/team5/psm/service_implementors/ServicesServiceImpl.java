package com.team5.psm.service_implementors;

import com.team5.psm.models.entity_models.Services;
import com.team5.psm.models.entity_models.SpaCenter;
import com.team5.psm.models.request_models.AddServiceRequest;
import com.team5.psm.models.request_models.DeleteServiceRequest;
import com.team5.psm.models.request_models.UpdateServiceRequest;
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
        model.addAttribute("service", service);

        servicesRepo.save(service);
        return "home";
    }

    @Override
    public String updateService(UpdateServiceRequest request, Model model) {
        Services service = servicesRepo.findById(request.getServiceId()).orElse(null);

        service.setName(request.getName());
        service.setDescription(request.getDescription());
        service.setPrice(request.getPrice());

        model.addAttribute("service", service);

        servicesRepo.save(service);
        return "home";
    }

    @Override
    public String deleteService(DeleteServiceRequest request, Model model) {
        model.addAttribute("msg", "delete successfully");
        servicesRepo.deleteById(request.getServiceId());
        return "home";
    }

}
