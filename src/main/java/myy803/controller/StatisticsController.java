package myy803.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import myy803.model.StudentRegistration;
import myy803.service.StudentRegistrationService;

@Controller
public class StatisticsController {

	@Autowired
	private StudentRegistrationService studentService;
	
	@GetMapping(value="/backToStudents/{instructor}/{courseId}")
	public String goBackToRegistrations(@PathVariable String instructor, 
			@PathVariable int courseId, Model model) {
		
		List<StudentRegistration> studentRegistrations = studentService
				.findRegistrationByCourseId(courseId);
		
		model.addAttribute("instructor", instructor);
		model.addAttribute("courseId", courseId);
		model.addAttribute("studentsList", studentRegistrations);
		return "Registrations";
	}
	
}