package myy803.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import myy803.service.InstructorService;

@RestController
public class InstructorController {

	@Autowired
	private InstructorService instructorService;
	

	public String getInstructorPassword(String username) {
		return instructorService.getInstructorPassword(username);
	}
	
}
