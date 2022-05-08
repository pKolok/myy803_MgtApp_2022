package myy803.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myy803.model.Instructor;
import myy803.dao.InstructorDAO;

@Service
public class InstructorServiceImpl implements InstructorService {
	
	@Autowired
	private InstructorDAO instructorDAO;

	
	@Override
	public boolean existsInstructor(String username) {
		return instructorDAO.existsInstructor(username);
	}
	
	@Override
	public String getInstructorPassword(String username) {
		return instructorDAO.getInstructorPassword(username);
	}

	@Override
	public void registerInstructor(Instructor instructor) {
		instructorDAO.registerInstructor(instructor);
	}

	@Override
	public Instructor getInstructor(String username) {
		return instructorDAO.getInstructor(username);
	}
}
