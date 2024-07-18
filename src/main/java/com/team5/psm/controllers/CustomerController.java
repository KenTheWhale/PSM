package com.team5.psm.controllers;

import com.team5.psm.services.AccountService;
import com.team5.psm.services.PetService;
import com.team5.psm.services.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.team5.psm.consts.FooterHTML;
import com.team5.psm.models.entity_models.Account;
import com.team5.psm.models.entity_models.Pet;
import com.team5.psm.models.entity_models.User;
import com.team5.psm.models.request_models.LoginRequest;
import com.team5.psm.models.request_models.RegisterRequest;
import com.team5.psm.models.request_models.UpdateProfileRequest;
import com.team5.psm.models.request_models.ViewAllPetOfUserRequest;
import com.team5.psm.models.request_models.ViewProfileUserRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class CustomerController {

	private final AccountService accountService;
	
	private final UserService userService;
	
	private final PetService petService;

	@PostMapping("/login")
	public String login(LoginRequest request, Model model, HttpSession session) {
		return accountService.login(request, model, session);
	}

	@PostMapping("/register")
	public String register(RegisterRequest request, Model model) {
		FooterHTML.setFooter(model);
		return accountService.register(request, model);
	}
	
	@GetMapping("/profile")
	public String loadProfile(Model model, HttpSession session) {
	    FooterHTML.setFooter(model);
	    
	    Account account = (Account) session.getAttribute("account");
	    if (account == null) {
	        model.addAttribute("error", "User not logged in");
	        return "login";
	    }

	    ViewProfileUserRequest request = new ViewProfileUserRequest();
	    request.setId(account.getId());
	    User user = userService.getUserbyAccountId(account.getId());
	    model.addAttribute("account", account);
	    model.addAttribute("user", user);
	    return userService.ViewProfileUser(request, model);
	}

	
	@PostMapping("/profile")
    public String updateProfile(UpdateProfileRequest request, Model model, HttpSession session) {
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            model.addAttribute("error", "User not logged in");
            return "login";
        }
       Long userId = account.getUser().getId();

       return userService.updateProfile(request, model, userId);
    }
	
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
	
	@GetMapping("/serviceDetail")
	public String loadServiceDetail(@RequestParam("id") Long id, Model model) {
	    userService.loadDetailService(id, model);
	    return "serviceDetail";
	}

	
}
