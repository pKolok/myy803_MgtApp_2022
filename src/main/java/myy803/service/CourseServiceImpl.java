package myy803.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myy803.dao.CourseDAO;
import myy803.dao.StudentRegistrationDAO;
import myy803.model.Course;
import myy803.model.StudentRegistration;

@Service
public class CourseServiceImpl implements CourseService {
	
	@Autowired
	private CourseDAO courseDAO;
	
	@Autowired
	private StudentRegistrationDAO studentRegistrationDAO;
	
	private List<StatisticStrategy> statCalculationStrategies;
	
	
	public HashMap<String, Double> getCourseStatistics(Course course) {
		
		HashMap<String, Double> statsInfo = new HashMap<String, Double>();
		
		List<StudentRegistration> students = studentRegistrationDAO
				.findRegistrationByCourseId(course.getId());
		
		for (StatisticStrategy strategy : statCalculationStrategies) {
			
			statsInfo.put(strategy.getStatisticName(), 
					strategy.calculateStatistic(students));
		}
		
		return statsInfo;
	}
	
	public List<StatisticStrategy> getStatCalculationStrategies() {
		return statCalculationStrategies;
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
