package myy803.MgtApp.repository;

import static org.junit.Assert.assertEquals;

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
@AutoConfigureTestDatabase(replace = Replace.NONE)	// Test again real database
@Rollback(true)	// Does not commit changes to database	
public class CourseRepositoryTests {
	
	@Autowired
	private CourseRepository courseRepo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testFindByInstructor() {
		
		// Create 2 instructors
		Instructor instructor1 = new Instructor("Teacher1", "abcde");
		entityManager.persist(instructor1);
		Instructor instructor2 = new Instructor("Teacher2", "edcba");
		entityManager.persist(instructor2);
		
		// Assign courses to instructors
		Course course1 = new Course("myy101", "English", "English Basics",
				instructor1, 1, 1, 0.5);
		entityManager.persist(course1);
		Course course2 = new Course("myy102", "Calculus1", "Calculus",
				instructor1, 1, 1, 0.5);
		entityManager.persist(course2);
		Course course3 = new Course("myy103", "Physics", "General Physics",
				instructor2, 1, 1, 0.5);
		entityManager.persist(course3);
		//entityManager.flush();
		
		// Test instructor1
		List<Course> courses = courseRepo.findByInstructor(instructor1);
		assertEquals(courses.size(), 2);
		assertEquals(courses.get(0).getName(), "myy101");
		assertEquals(courses.get(1).getName(), "myy102");
		
		// Test instructor2
		courses = courseRepo.findByInstructor(instructor2);
		assertEquals(courses.size(), 1);
		assertEquals(courses.get(0).getName(), "myy103");
	}
	
}