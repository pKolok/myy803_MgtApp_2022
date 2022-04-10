package myy803.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import myy803.model.Instructor;
import myy803.service.InstructorService;

@Controller
public class RegisterController {

	@Autowired
	private InstructorService instructorService;
	
	@RequestMapping(value = "/register", method = RequestMethod.POST, 
			params = "register")
	public String register(@RequestParam String username, String password1,
			String password2, Model model) {
		
		/* User-name checks*/
		// check if user-name is empty
		if (username.isBlank()) {
			model.addAttribute("error", "Please provide username.");
			return "Register";	
		}
		
		// check if user-name already exists
		if (instructorService.existsInstructor(username)) {
			model.addAttribute("error", "Username already used. Please choose "
					+ "another.");
			return "Register";	
		}
		
		/* User-name checks*/
		// check if password field is empty
		if (password1.isBlank()) {
			model.addAttribute("error", "Please provide password.");
			return "Register";	
		}
		
		// check if give passwords match
		if (!password1.equals(password2))
		{
			model.addAttribute("error", "Passwords do not match. Please try "
					+ "again.");
			return "Register";			
		}
		
		// if all is good, create a new instructor login
		Instructor newInstructor = new Instructor(username, password1);
		instructorService.registerInstructor(newInstructor);
		
		model.addAttribute("info", "Registration successfull. Please sign in");
		return "Index";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST, 
			params = "cancel")
	public String cancel(@RequestParam String username, String password1, 
			String password2, Model model) {
		System.out.println("Cancelling register...");

		// Take me to the register page
		return "Index";
	}
	
}
