package myy803.dao;

import java.util.List;

import myy803.model.Course;

public interface CourseDAO {
	
	public List<Course> findCourseByInstructorLogin(String instructor);
	
	public void delete(int id);
	
	public void save(Course course);
	
	public void update(Course course);
	
	public Course getCourse(int id);
}
