package myy803.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myy803.model.Course;
import myy803.model.StudentRegistration;
import myy803.repository.StudentRegistrationRepository;

@Service
public class StudentRegistrationDAOImpl implements StudentRegistrationDAO{

	private EntityManager entityManager;
	
	@Autowired
	private StudentRegistrationRepository studentRegRepository;
	
	public StudentRegistrationDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public List<StudentRegistration> findRegistrationByCourseId(int id) {
		
		Course course = entityManager.find(Course.class, id);
		
		List<StudentRegistration> studentRegs = studentRegRepository
				.findByCourse(course);
		
		return studentRegs;
	}

	@Override
	public void delete(int id) {
		studentRegRepository.deleteById(id);
	}

	@Override
	public void save(StudentRegistration studentRegistration) {
		studentRegRepository.save(studentRegistration);	
	}

	@Override
	public void update(StudentRegistration pStudentRegistration) {
		Optional<StudentRegistration> optionalStudentRegistration = 
				studentRegRepository.findById(pStudentRegistration.getId());
		
		if (optionalStudentRegistration.isPresent()) {
			StudentRegistration studentRegistration = 
					optionalStudentRegistration.get();
			
			studentRegistration.setName(pStudentRegistration.getName());
			studentRegistration.setSemester(pStudentRegistration.getSemester());
			studentRegistration.setYearOfStudies(
					pStudentRegistration.getYearOfStudies());
			studentRegistration.setCourse(pStudentRegistration.getCourse());
			studentRegistration.setYearOfRegistration(
					pStudentRegistration.getYearOfRegistration());
			studentRegistration.setProjectGrade(
					pStudentRegistration.getProjectGrade());
			studentRegistration.setExamGrade(
					pStudentRegistration.getExamGrade());
			studentRegistration.setGrade(studentRegistration.getGrade());
			
			// if we send an object with an existing id, it changes the columns 
			// already found in the database
			studentRegRepository.save(studentRegistration);
		}
	}
	
	@Override
	public StudentRegistration getStudentRegistration(int id) {
		Optional<StudentRegistration> optionalStudentRegistration = 
				studentRegRepository.findById(id);
		
		if (optionalStudentRegistration.isPresent()) {
			StudentRegistration studentRegistration = 
					optionalStudentRegistration.get();
			
			return studentRegistration;
		}	
		return null;	
	}
	
}