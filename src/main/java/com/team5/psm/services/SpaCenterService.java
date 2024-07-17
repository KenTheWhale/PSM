package com.team5.psm.services;

import com.team5.psm.models.request_models.UpdateCenterRequest;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public interface SpaCenterService {
    String updateSpaCenter(UpdateCenterRequest request, Model model);
}
