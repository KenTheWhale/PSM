package com.team5.psm.consts;

import org.springframework.ui.Model;

public class FooterHTML {
	public static final String SHOP_DESCRIPTION = "This is a pet spa shop";
	public static final String SHOP_ADDRESS = "Nga 3 chuong cho";
	public static final String SHOP_PHONE = "(+84) 989575268";
	public static final String SHOP_EMAIL = "petspa@gmail.com";
	
	public static void setFooter(Model model) {
		model.addAttribute("description", SHOP_DESCRIPTION);
		model.addAttribute("address", SHOP_ADDRESS);
		model.addAttribute("phone", SHOP_PHONE);
		model.addAttribute("email", SHOP_EMAIL);
	}
}
