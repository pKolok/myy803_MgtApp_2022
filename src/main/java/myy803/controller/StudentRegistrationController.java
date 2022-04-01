package myy803.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import myy803.model.StudentRegistration;
import myy803.repository.StudentRegistrationRepository;

@RestController
@RequestMapping(path="MgtApp/StudentRegistrations")
public class StudentRegistrationController {
	
	@Autowired
	private StudentRegistrationRepository studentRegistrationRepository;

	/* in cmd:
	 * localhost:8080/MgtApp/StudentRegistrations/add -d name=Panos -d yearOfStudies=4 -d semester=8 -d yearOfRegistration=2020 -d projectGrade=8 -d examGrade=6 -d grade=7*/
	
	@PostMapping(path="/add")
	public String addNewStudentRegistration (
			@RequestParam String name,  @RequestParam Integer yearOfStudies, 
			@RequestParam Integer semester, 
			@RequestParam Integer yearOfRegistration, 
			@RequestParam double projectGrade, @RequestParam double examGrade,
			@RequestParam double grade) {

		studentRegistrationRepository.save(new StudentRegistration(name,
				yearOfStudies, semester, yearOfRegistration, projectGrade, 
				examGrade, grade));
		
//		studentRegistrationRepository.save(
//				new StudentRegistration("Panos", 4, 8, 2020, 9.3, 8.0, 8.5));
//		studentRegistrationRepository.save(
//				new StudentRegistration("Maria", 3, 6, 2021, 8.3, 10.0, 9.5));
//		studentRegistrationRepository.save(
//				new StudentRegistration("Alex", 5, 10, 2019, 5.2, 8.0, 7.0));
		
		
		return "Saved";
	}

	@GetMapping(path="/all")
	public @ResponseBody Iterable<StudentRegistration> getAllstudentRegistrations() {
		return studentRegistrationRepository.findAll();
	}
  
}