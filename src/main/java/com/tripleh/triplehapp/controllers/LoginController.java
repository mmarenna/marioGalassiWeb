package com.tripleh.triplehapp.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;




@Controller
public class LoginController {

	private static final Log LOGGER = LogFactory.getLog(LoginController.class);

//	@GetMapping("/")
//	public String redirectToLogin() {
//		LOGGER.info("METHOD: redirectToLogin");
//		return "redirect:/login";
//	}

	@GetMapping("/login")
	public String showLoginInform(Model model, @RequestParam(name = "error", required = false) String error,
			@RequestParam(name = "logout", required = false) String logout) {
		LOGGER.info("METHOD: showLoginInform() PARAMS----- Error:" + error + "Logout:" + logout);
//		model.addAttribute("userCredentials", new UserCredential());
		model.addAttribute("error", error);
		model.addAttribute("logout", logout);
		return 	"views/login";
	}

	@GetMapping({"/loginsuccess"})
	public String loginCheck() {

		return "redirect:/admin";
	}
}
