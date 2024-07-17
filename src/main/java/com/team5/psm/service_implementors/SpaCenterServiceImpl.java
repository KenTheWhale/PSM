package com.team5.psm.service_implementors;

import com.team5.psm.consts.Role;
import com.team5.psm.models.entity_models.Account;
import com.team5.psm.models.entity_models.SpaCenter;
import com.team5.psm.models.request_models.UpdateCenterRequest;
import com.team5.psm.repositories.AccountRepo;
import com.team5.psm.repositories.SpaCenterRepo;
import com.team5.psm.services.SpaCenterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
@RequiredArgsConstructor
public class SpaCenterServiceImpl implements SpaCenterService {

    private final SpaCenterRepo spaCenterRepo;
    private final AccountRepo accountRepo;

    @Override
    public String updateSpaCenter(UpdateCenterRequest request, Model model) {
        Account manager = accountRepo.findById(request.getManagerId()).orElse(null);
        if (manager == null) {
            model.addAttribute("msg", "need to assign an existed account");
            return "home";
        } else if (manager.getRole().equals(Role.SHOP_OWNER)) {
            model.addAttribute("msg", "need to assign a manager role account");
            return "home";
        }
        SpaCenter spaCenter = spaCenterRepo.findById(request.getCenterId()).orElse(null);
        spaCenter.setAccount(manager);
        spaCenter.setName(request.getName());
        spaCenter.setAddress(request.getAddress());

        spaCenterRepo.save(spaCenter);
        return "home";
    }
}
