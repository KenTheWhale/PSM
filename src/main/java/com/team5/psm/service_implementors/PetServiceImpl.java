package com.team5.psm.service_implementors;

import java.util.List;

import org.springframework.stereotype.Service;

import com.team5.psm.models.entity_models.Pet;
import com.team5.psm.models.request_models.ViewAllPetOfUserRequest;
import com.team5.psm.repositories.AccountRepo;
import com.team5.psm.repositories.PetRepo;
import com.team5.psm.services.PetService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {
	private final PetRepo petRepo;

	@Override
	public List<Pet> viewAllPetOfUser(ViewAllPetOfUserRequest request) {
		// TODO Auto-generated method stub
		List<Pet> petList = petRepo.findAllByUser_Account_Id(request.getId());
		return petList;

	}

}
