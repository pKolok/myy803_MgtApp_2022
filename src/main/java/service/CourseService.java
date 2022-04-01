package service;

import java.util.List;

import myy803.model.Course;

public interface CourseService {

	public List<Course> findCourseByInstructorLogin(String instructor);
	
	public void delete(int id);
	
	public void save(Course course);
	
	public void update(Course course);
}
