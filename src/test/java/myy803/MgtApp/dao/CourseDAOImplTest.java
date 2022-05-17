package myy803.MgtApp.dao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import myy803.dao.CourseDAOImpl;
import myy803.model.Course;
import myy803.model.Instructor;
import myy803.repository.CourseRepository;

class CourseDAOImplTest {
	
	private static CourseRepository mockcourseRepository;
	private static EntityManager mockEntityManager;
	private static Instructor maria;
	private static Course english, calculus, physics;
	private static List<Course> courses;
	private static CourseDAOImpl courseDAOImpl;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		// Set up mocks
		mockcourseRepository  = mock(CourseRepository.class);
		mockEntityManager = mock(EntityManager.class);
		
		// Set up instructors
		maria = new Instructor("Maria", String.valueOf("12345".hashCode()));
		
		// Set up courses
		english = new Course("myy101", "English", "Introduction to English", 
				maria, 1, 1, 0.5);
		calculus = new Course("myy102", "Calculus", "Introduction to Calculus", 
				maria, 1, 1, 0.5);
		physics = new Course("myy103", "Physics", "Introduction to Physics", 
				maria, 1, 1, 0.5);
		courses = new ArrayList<Course>();
		courses.add(english); courses.add(calculus); courses.add(physics);
		
		// Set up class under test
		courseDAOImpl = new CourseDAOImpl(mockcourseRepository,
				mockEntityManager);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testFindCourseByInstructorLoginHappyDay() {
		when(mockEntityManager.find(Instructor.class, "Maria"))
		.thenReturn(maria);
		when(mockcourseRepository.findByInstructor(maria)).thenReturn(courses);
		
		assertEquals(courseDAOImpl.findCourseByInstructorLogin("Maria"),
				courses);
	}
	
	@Test
	void testFindCourseByInstructorLoginEmptyList() {
		when(mockEntityManager.find(Instructor.class, "Maria"))
		.thenReturn(maria);
		List<Course> emptyList = new ArrayList<Course>();
		when(mockcourseRepository.findByInstructor(maria))
		.thenReturn(emptyList);
		
		assertEquals(courseDAOImpl.findCourseByInstructorLogin("Maria"),
				emptyList);
	}
	
	@Test
	void testGetCourseHappyDay() {
		when(mockcourseRepository.findById(1)).thenReturn(Optional.of(english));
		
		assertEquals(courseDAOImpl.getCourse(1), english);
	}
	
	@Test
	void testGetCourseNotPresent() {
		when(mockcourseRepository.findById(1)).thenReturn(Optional.empty());
		
		assertEquals(courseDAOImpl.getCourse(1), null);
	}

}