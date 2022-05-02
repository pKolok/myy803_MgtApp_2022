package myy803.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import myy803.model.StudentRegistration;
import myy803.service.StudentRegistrationService;

@Controller
//@RequestMapping(path="MgtApp/StudentRegistrations")
public class StudentRegistrationController {
	
	@Autowired
	private StudentRegistrationService studentService;

	
	@RequestMapping(value = "/studentRegs/{id}", method = RequestMethod.POST)
	public String showStudentRegistrations(@PathVariable int id, Model model) {
		
		// if all is good
		List<StudentRegistration> studentRegistrations = studentService
				.findRegistrationByCourseId(id);
		
		model.addAttribute("studentsList", studentRegistrations);
		return "Registrations";
	}
	
	
	@PostMapping(path="/addStudent")
	public String addNewStudentRegistration (
			@RequestParam String name,  @RequestParam Integer yearOfStudies, 
			@RequestParam Integer semester, 
			@RequestParam Integer yearOfRegistration, 
			@RequestParam double projectGrade, @RequestParam double examGrade,
			@RequestParam double grade) {

		studentService.save(new StudentRegistration(name,
				yearOfStudies, semester, yearOfRegistration, projectGrade, 
				examGrade, grade));		
		
		return "Saved";
	}
  
}