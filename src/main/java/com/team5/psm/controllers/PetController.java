package com.team5.psm.controllers;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.team5.psm.consts.FooterHTML;
import com.team5.psm.models.entity_models.Account;
import com.team5.psm.models.request_models.AddPetRequest;
import com.team5.psm.models.request_models.DeletePetRequest;
import com.team5.psm.models.request_models.UpdatePetRequest;
import com.team5.psm.services.PetService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PetController {
	private final PetService petService;
	
	@PostMapping("/addPet")
    public String addPet(AddPetRequest request, Model model, HttpSession session) {
        FooterHTML.setFooter(model);
        
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            model.addAttribute("error", "User not logged in");
            return "login";
        }
        
        return petService.addPet(request, account.getId(), request.getSpeciesId(), model);
    }
	
	@PostMapping("/updatePet")
	public String updatePet(UpdatePetRequest request, Model model, HttpSession session) {
		Account account = (Account) session.getAttribute("account");
		if (account == null) {
			model.addAttribute("error", "User not logged in");
			return "login";
		}
		
		return petService.updatePet(request, model);
	}
	
	@DeleteMapping("/deletePet")
    public String deletePet(DeletePetRequest request, Model model, HttpSession session) {
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            model.addAttribute("error", "User not logged in");
            return "login";
        }
        
        return petService.deletePet(request, model);
    }
}
