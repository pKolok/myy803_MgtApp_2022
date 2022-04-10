package myy803.MgtApp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import myy803.model.Course;
import myy803.model.Instructor;
import myy803.repository.CourseRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class CourseTests {
	
	@Autowired
	private CourseRepository courseRepo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testAddOneCourse() {
		Instructor instructor = entityManager.find(Instructor.class, "Teacher1");
		
		Course course = new Course("myy103", "Physics", "Electronics", 
				instructor, 1, 1);
		
		Course savedCourse = courseRepo.save(course);
		
		assertTrue(savedCourse.getId() > 0);
	}
	
	@Test
	public void testGetCoursesByInstructor() {
		Instructor instructor = new Instructor();
		instructor.setUsername("Teacher1");
		
		List<Course> courses = courseRepo.findByInstructor(instructor);
		
		assertEquals(courses.size(), 1);
	}
	
}
