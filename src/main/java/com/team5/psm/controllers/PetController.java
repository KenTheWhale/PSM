package com.team5.psm.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.team5.psm.consts.FooterHTML;
import com.team5.psm.models.entity_models.Account;
import com.team5.psm.models.entity_models.Pet;
import com.team5.psm.models.request_models.AddPetRequest;
import com.team5.psm.models.request_models.ViewAllPetOfUserRequest;
import com.team5.psm.services.PetService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PetController {
	private final PetService petService;

	@GetMapping("/pet")
	public String loadPets(Model model, HttpSession session) {
		FooterHTML.setFooter(model);
		Account account = (Account) session.getAttribute("account");
		if (account == null) {
			model.addAttribute("error", "User not logged in");
			return "login";
		}

		ViewAllPetOfUserRequest request = new ViewAllPetOfUserRequest();
		request.setId(account.getId());

		List<Pet> petList = petService.viewAllPetOfUser(request);
		model.addAttribute("petList", petList);

		return "redirect:/pet";
	}

	@PostMapping("/pet")
	public String addPet(AddPetRequest request,Model model, HttpSession session) {
		FooterHTML.setFooter(model);
		Account account = (Account) session.getAttribute("account");
		if (account == null) {
			model.addAttribute("error", "User not logged in");
			return "login";
		}
		
		return petService.AddPet(request,account.getId(), account.getUser().getId(), model);
	}
}
