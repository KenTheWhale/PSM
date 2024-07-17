package com.team5.psm.service_implementors;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.team5.psm.models.entity_models.Account;
import com.team5.psm.models.entity_models.Pet;
import com.team5.psm.models.entity_models.Species;
import com.team5.psm.models.entity_models.User;
import com.team5.psm.models.request_models.AddPetRequest;
import com.team5.psm.models.request_models.UpdatePetRequest;
import com.team5.psm.models.request_models.ViewAllPetOfUserRequest;
import com.team5.psm.repositories.AccountRepo;
import com.team5.psm.repositories.PetRepo;
import com.team5.psm.repositories.SpeciesRepo;
import com.team5.psm.repositories.UserRepo;
import com.team5.psm.services.PetService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {
	private final PetRepo petRepo;

	private final UserRepo userRepo;

	private final SpeciesRepo speciesRepo;

	private final AccountRepo accountRepo;

	@Override
	public List<Pet> viewAllPetOfUser(ViewAllPetOfUserRequest request) {
		// TODO Auto-generated method stub
		List<Pet> petList = petRepo.findAllByUser_Account_Id(request.getId());
		return petList;

	}

	@Override
	public String addPet(AddPetRequest request, Long accID, Long speciesID, Model model) {
		String petName = request.getName();
		String description = request.getDescription();

		if (!checkIfStringIsValid(petName)) {
			model.addAttribute("error", "Please fill in Pet Name.");
			return "pet";
		}

		Species species = speciesRepo.findById(speciesID).orElse(null);

		User user = userRepo.findByAccount_Id(accID);

		Pet pet = Pet.builder().name(petName).description(description).species(species).user(user).build();
		petRepo.save(pet);
		return "redirect:/pet";
	}

	@Override
	public String updatePet(UpdatePetRequest request, Model model) {
		Long petId = request.getId();
		Pet pet = petRepo.findById(petId).orElse(null);
		if (pet == null) {
			model.addAttribute("error", "Pet not found");
			return "pet";
		}
		pet.setName(request.getName());
		pet.setDescription(request.getDescription());

		petRepo.save(pet);
		return "redirect:/pet";
	}

	private boolean checkIfStringIsValid(String input) {
		return input != null && !input.isEmpty();
	}

}
