package com.team5.psm.service_implementors;

import com.team5.psm.models.entity_models.DraftBooking;
import com.team5.psm.models.entity_models.Pet;
import com.team5.psm.models.entity_models.ServicePlan;
import com.team5.psm.models.request_models.CreateDraftBookingRequest;
import com.team5.psm.models.request_models.DeleteDraftBookingRequest;
import com.team5.psm.models.request_models.UpdateDraftBookingRequest;
import com.team5.psm.repositories.DraftBookingRepo;
import com.team5.psm.repositories.PetRepo;
import com.team5.psm.repositories.ServicePlanRepo;
import com.team5.psm.services.DraftBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;


@Service
@RequiredArgsConstructor
public class DraftBookingServiceImpl implements DraftBookingService {

    private final DraftBookingRepo draftBookingRepo;
    private final ServicePlanRepo servicePlanRepo;
    private final PetRepo petRepo;

    @Override
    public String createDraftBooking(CreateDraftBookingRequest request, Model model) {
        Long petId = request.getPetId();
        Long planId = request.getPlanId();

        Pet pet = petRepo.findById(petId).orElse(null);
        ServicePlan plan = servicePlanRepo.findById(planId).orElse(null);

        DraftBooking draftBooking = DraftBooking.builder()
                                                .pet(pet)
                                                .servicePlan(plan)
                                                .price(plan.getPrice())
                                                .build();
        model.addAttribute("draftBooking", draftBooking);

        draftBookingRepo.save(draftBooking);
        return "home";
    }

    @Override
    public String updateDraftBooking(UpdateDraftBookingRequest request, Model model) {
    	Long petId = request.getPetId();
        Long planId = request.getPlanId();

        DraftBooking draftBooking = draftBookingRepo.findById(request.getId()).orElse(null);
        Pet pet = petRepo.findById(petId).orElse(null);
        ServicePlan plan = servicePlanRepo.findById(planId).orElse(null);

        draftBooking.setPet(pet);
        draftBooking.setServicePlan(plan);
        draftBooking.setPrice(plan.getPrice());
        
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
