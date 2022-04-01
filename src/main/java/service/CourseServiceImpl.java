package service;

import java.util.HashMap;
import java.util.List;

import myy803.model.Course;

public class CourseServiceImpl implements CourseService {

	private List<StatisticStrategy> statCalculationStrategies;
	
	public CourseServiceImpl() {}
	
	public HashMap<String, Double> getCourseStatistics() {
		return null;
	}
	
	public List<StatisticStrategy> getStatCalculationStrategies() {
		return null;
	}
	
	public void setStatCalculationStrategies(List<StatisticStrategy> 
		statisticStrategies) {
		statCalculationStrategies = statisticStrategies;
	}
	
	@Override
	public List<Course> findCourseByInstructorLogin(String instructor) {
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
