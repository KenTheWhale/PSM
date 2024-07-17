package com.team5.psm.service_implementors;

import com.team5.psm.models.entity_models.DraftBooking;
import com.team5.psm.models.entity_models.Pet;
import com.team5.psm.models.entity_models.Services;
import com.team5.psm.models.request_models.CreateDraftBookingRequest;
import com.team5.psm.models.request_models.DeleteDraftBookingRequest;
import com.team5.psm.models.request_models.UpdateDraftBookingRequest;
import com.team5.psm.repositories.DraftBookingRepo;
import com.team5.psm.repositories.PetRepo;
import com.team5.psm.repositories.ServicesRepo;
import com.team5.psm.repositories.SpaCenterRepo;
import com.team5.psm.services.DraftBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;


@Service
@RequiredArgsConstructor
public class DraftBookingServiceImpl implements DraftBookingService {

    private final DraftBookingRepo draftBookingRepo;
    private final ServicesRepo servicesRepo;
    private final PetRepo petRepo;
    private final SpaCenterRepo spaCenterRepo;

    @Override
    public String createDraftBooking(CreateDraftBookingRequest request, Model model) {
        Long petId = request.getPetId();
        String spaName = request.getSpaName();
        String serviceName = request.getServiceName();
        float price = request.getPrice();

        Pet pet = petRepo.findById(petId).orElse(null);
        Services services = servicesRepo.findByNameAndSpaCenter(serviceName, spaCenterRepo.findByName(spaName).orElse(null)).orElse(null);

        DraftBooking draftBooking = DraftBooking.builder()
                                                .pet(pet)
                                                .service(services)
                                                .price(price)
                                                .build();
        model.addAttribute("draftBooking", draftBooking);

        draftBookingRepo.save(draftBooking);
        return "home";
    }

    @Override
    public String updateDraftBooking(UpdateDraftBookingRequest request, Model model) {
        Long petId = request.getPetId();
        String spaName = request.getSpaName();
        String serviceName = request.getServiceName();
        float price = request.getPrice();

        DraftBooking draftBooking = draftBookingRepo.findById(request.getId()).orElse(null);
        Pet pet = petRepo.findById(petId).orElse(null);
        Services services = servicesRepo.findByNameAndSpaCenter(serviceName, spaCenterRepo.findByName(spaName).orElse(null)).orElse(null);

        draftBooking.setPet(pet);
        draftBooking.setService(services);
        draftBooking.setPrice(price);
        
        model.addAttribute("draftBooking", draftBooking);

        draftBookingRepo.save(draftBooking);
        return "home";
    }

    @Override
    public String deleteDraftBooking(DeleteDraftBookingRequest request, Model model) {
        model.addAttribute("msg", "check in success");
        draftBookingRepo.deleteById(request.getId());
        return "home";
    }

}
