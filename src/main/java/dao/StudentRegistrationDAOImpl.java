package dao;

import java.util.List;

import myy803.model.StudentRegistration;

public class StudentRegistrationDAOImpl implements StudentRegistrationDAO{

	private EntityManager entityManager;
	
	public StudentRegistrationDAOImpl(EntityManager entityManager) {
		this.entityManager = EntityManager;
	}
	
	@Override
	public List<StudentRegistration> findRegistrationByCourseId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(StudentRegistration studentRegistration) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(StudentRegistration studentRegistration) {
		// TODO Auto-generated method stub
		
	}
	
	
}
