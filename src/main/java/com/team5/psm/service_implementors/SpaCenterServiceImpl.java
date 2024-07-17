package com.team5.psm.service_implementors;

import com.team5.psm.models.entity_models.SpaCenter;
import com.team5.psm.models.entity_models.User;
import com.team5.psm.models.request_models.UpdateCenterProfileRequest;
import com.team5.psm.repositories.SpaCenterRepo;
import com.team5.psm.services.SpaCenterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class SpaCenterServiceImpl extends BaseServiceImpl implements SpaCenterService {
    private final SpaCenterRepo spaCenterRepo;
    @Override
    public String getCenterProfile(Long centerId, Model model) {
        SpaCenter spaCenter = spaCenterRepo.findById(centerId).orElse(null);

        if (spaCenter == null) {
            model.addAttribute("error", "Center not found.");
            return "home";
        }

        model.addAttribute("spaCenter", spaCenter);
        return "center-profile";
    }

    @Override
    public String updateCenterProfile(UpdateCenterProfileRequest request, Model model) {
        Long centerId = request.getCenterId();
        String name = request.getName();
        String address = request.getAddress();

        if (!checkIfStringIsValid(name) || !checkIfStringIsValid(address)) {
            model.addAttribute("error", "Name and address are required.");
            return "home";
        }

        SpaCenter spaCenter = spaCenterRepo.findById(centerId).orElse(null);

        if (spaCenter == null) {
            model.addAttribute("error", "Center not found.");
            return "home";
        }

        spaCenter.setName(name);
        spaCenter.setAddress(address);
        spaCenterRepo.save(spaCenter);

        return "center-profile";
    }
}
