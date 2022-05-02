package myy803.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myy803.dao.CourseDAO;
import myy803.model.Course;

@Service
public class CourseServiceImpl implements CourseService {
	
	@Autowired
	private CourseDAO courseDAO;
	
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
		return courseDAO.findCourseByInstructorLogin(instructor);
	}

	@Override
	public void delete(int id) {
		courseDAO.delete(id);
	}

	@Override
	public void save(Course course) {
		courseDAO.save(course);
	}

	@Override
	public void update(Course course) {
		courseDAO.update(course);
	}
	
	@Override
	public Course getCourse(int id) {
		return courseDAO.getCourse(id);
	}

}
