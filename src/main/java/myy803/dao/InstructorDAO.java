package myy803.dao;

import myy803.model.Instructor;

public interface InstructorDAO {

	public boolean existsInstructor(String username);
	
	public String getInstructorPassword(String username);
	
	public void registerInstructor(Instructor instructor);
	
	public Instructor getInstructor(String username);
}
