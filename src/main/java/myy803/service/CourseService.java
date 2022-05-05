package myy803.service;

import java.util.HashMap;
import java.util.List;

import myy803.model.Course;

public interface CourseService {

	public List<Course> findCourseByInstructorLogin(String instructor);
	
	public void delete(int id);
	
	public void save(Course course);
	
	public void update(Course course);
	
	public Course getCourse(int id);
	
	public HashMap<String, Double> getCourseStatistics(Course course);
	
	public List<StatisticStrategy> getStatCalculationStrategies();
	
	public void setStatCalculationStrategies(List<StatisticStrategy> 
		statisticStrategies);
}
