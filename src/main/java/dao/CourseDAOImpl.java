package dao;

import java.util.List;

import myy803.model.Course;

public class CourseDAOImpl implements CourseDAO {
	
	private EntityManager entityManager;
	
	public CourseDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public List<Course> finfCourseByInstructorLogin(String instructor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(Course course) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Course course) {
		// TODO Auto-generated method stub
		
	}

	
}
