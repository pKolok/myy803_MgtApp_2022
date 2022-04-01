package dao;

import java.util.List;

import myy803.model.StudentRegistration;

public interface StudentRegistrationDAO {

	public List<StudentRegistration> findRegistrationByCourseId(int id);
	
	public void delete(int id);
	
	public void save(StudentRegistration studentRegistration);
	
	public void update(StudentRegistration studentRegistration);
}
