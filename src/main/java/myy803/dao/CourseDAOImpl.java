package myy803.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myy803.model.Course;
import myy803.model.Instructor;
import myy803.repository.CourseRepository;

@Service
public class CourseDAOImpl implements CourseDAO {
	
	private EntityManager entityManager;
	
	@Autowired
	private CourseRepository courseRepository;
	
	public CourseDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public List<Course> findCourseByInstructorLogin(String pInstructor) {
		Instructor instructor = entityManager.find(Instructor.class, pInstructor);
		
		List<Course> courses = courseRepository.findByInstructor(instructor);
		
		return courses;
	}

	@Override
	public void delete(int id) {
		courseRepository.deleteById(id);
	}

	@Override
	public void save(Course course) {
		courseRepository.save(course);
	}

	@Override
	public void update(Course pCourse) {
		Optional<Course> optionalCourse = courseRepository.findById(pCourse.getId());
		
		if (optionalCourse.isPresent()) {
			Course course = optionalCourse.get();
			
			course.setName(pCourse.getName());
			course.setInstructor(pCourse.getInstructor());
			course.setDescription(pCourse.getDescription());
			course.setSyllabus(pCourse.getSyllabus());
			course.setYear(pCourse.getYear());
			course.setSemester(pCourse.getSemester());
			
			// if we send an object with an existing id, it changes the columns 
			// already found in the database
			courseRepository.save(course);
		}
	}

	
}
