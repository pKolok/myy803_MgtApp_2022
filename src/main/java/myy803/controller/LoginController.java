package myy803.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import myy803.model.Course;
import myy803.service.CourseService;
import myy803.service.InstructorService;

@Controller
public class LoginController {

	@Autowired
	private InstructorService instructorService;
	
	@Autowired
	private CourseService courseService;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, 
			params = "login")
	public String login(@RequestParam String username, String password, 
			Model model) {	
	
		// if user-name is empty
		if (username.isBlank()) {
			model.addAttribute("error", "Please provide username");
			return "Index";
		}
		
		// if user-name does not exist in database
		if(!instructorService.existsInstructor(username)) {
			model.addAttribute("error", "No such user. Please register");
			return "Index";
		}
		
		String hashPassword = String.valueOf(password.hashCode());
		
		String correctHashPassword = instructorService
				.getInstructorPassword(username);
		
		// if password is not correct
		if (!hashPassword.equals(correctHashPassword)) {
			model.addAttribute("error", "Incorrect username and/or password");
			return "Index";		
		}
		
		// if all is good
		List<Course> courses = courseService
				.findCourseByInstructorLogin(username);
		
		model.addAttribute("courseList", courses);
		model.addAttribute("instructor", username);
		return "Courses";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, 
			params = "register")
	public String showRegisterForm(Model model) {
		return "Register";
	}
	
}