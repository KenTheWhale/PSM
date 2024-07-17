package com.team5.psm.services;

import com.team5.psm.models.request_models.UpdateCenterProfileRequest;
import com.team5.psm.models.request_models.UpdateCenterRequest;

import org.springframework.ui.Model;

public interface SpaCenterService {
    String getCenterProfile(Long centerId, Model model);
	String updateSpaCenter(UpdateCenterRequest request, Model model);
}
