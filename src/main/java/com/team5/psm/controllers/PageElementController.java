package com.team5.psm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.team5.psm.consts.FooterHTML;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PageElementController {

	@GetMapping("/")
	public String home(Model model) {
		FooterHTML.setFooter(model);
		return "home";
	}
	
	@GetMapping("/home")
	public String homeIndex(Model model) {
		FooterHTML.setFooter(model);
		return "home";
	}
	
	@GetMapping("/about")
	public String about(Model model) {
		FooterHTML.setFooter(model);
		return "about";
	}
	
	@GetMapping("/vet")
	public String vet(Model model) {
		FooterHTML.setFooter(model);
		return "vet";
	}
	
	@GetMapping("/services")
	public String services(Model model) {
		FooterHTML.setFooter(model);
		return "services";
	}
	
	@GetMapping("/login")
	public String login(Model model) {
		FooterHTML.setFooter(model);
		return "Login";
	}
	
}
