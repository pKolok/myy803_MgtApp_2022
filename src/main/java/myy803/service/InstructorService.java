package myy803.service;

import myy803.model.Instructor;

public interface InstructorService {
	
	public boolean existsInstructor(String username);
	
	public String getInstructorPassword(String username);
	
	public void registerInstructor(Instructor instructor);
	
	public Instructor getInstructor(String username);
}
