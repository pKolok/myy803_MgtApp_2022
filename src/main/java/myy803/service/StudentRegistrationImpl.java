package myy803.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myy803.dao.StudentRegistrationDAO;
import myy803.model.StudentRegistration;

@Service
public class StudentRegistrationImpl implements StudentRegistrationService {

	@Autowired
	private StudentRegistrationDAO studentRegistrationDAO;
	
	public StudentRegistrationImpl() {}  
	
	@Override
	public List<StudentRegistration> findRegistrationByCourseId(int id) {
		return studentRegistrationDAO.findRegistrationByCourseId(id);
	}

	@Override
	public void delete(int id) {
		studentRegistrationDAO.delete(id);
	}

	@Override
	public void save(StudentRegistration studentRegistration) {
		studentRegistrationDAO.save(studentRegistration);
	}

	@Override
	public void update(StudentRegistration studentRegistration) {
		studentRegistrationDAO.update(studentRegistration);
	}
	
	@Override
	public StudentRegistration getStudentRegistration(int id) {
		return studentRegistrationDAO.getStudentRegistration(id);
	}
}
