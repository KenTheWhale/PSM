package com.team5.psm.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.team5.psm.models.entity_models.Pet;
import com.team5.psm.models.request_models.AddPetRequest;
import com.team5.psm.models.request_models.ViewAllPetOfUserRequest;

@Service
public interface PetService {
	List<Pet> viewAllPetOfUser(ViewAllPetOfUserRequest request);
	
	String AddPet(AddPetRequest request, Long accID, Long speciesID, Model model);

}
