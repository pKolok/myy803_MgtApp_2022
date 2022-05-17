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

import myy803.dao.StudentRegistrationDAOImpl;
import myy803.model.Course;
import myy803.model.Instructor;
import myy803.model.StudentRegistration;
import myy803.repository.StudentRegistrationRepository;

class StudentRegistrationDAOImplTest {

	private static StudentRegistrationRepository mockStudentRepository;
	private static EntityManager mockEntityManager;
	private static Instructor maria;
	private static Course english, calculus, physics;
	private static List<Course> courses;
	private static StudentRegistration pablo, iman, george, andre;
	private static List<StudentRegistration> students;
	private static StudentRegistrationDAOImpl studentDAOimpl;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		// Set up mocks
		mockStudentRepository  = mock(StudentRegistrationRepository.class);
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
		
		// Set up students
		students = new ArrayList<StudentRegistration>();
		pablo = new StudentRegistration("pablo", 4, 8, english, 2018, 5.0, 8.0,
				6.0);
		iman = new StudentRegistration("iman", 3, 6, calculus, 2019, 5.0, 7.0,
				6.5);
		george = new StudentRegistration("george", 5, 10, english, 2017, 8.0,
				9.0, 7.5);
		andre = new StudentRegistration("andre", 2, 4, english, 2020, 10.0, 8.0,
				9.5);
		
		// Set up class under test
		studentDAOimpl = new StudentRegistrationDAOImpl(mockStudentRepository,
				mockEntityManager);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		students.add(pablo); students.add(iman); students.add(george);
		students.add(andre);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testFindRegistrationByCourseIdHappyDay() {
		when(mockEntityManager.find(Course.class, 1)).thenReturn(english);
		when(mockStudentRepository.findByCourse(english)).thenReturn(students);
		
		assertEquals(studentDAOimpl.findRegistrationByCourseId(1), students);
	}
	
	@Test
	void testFindRegistrationByCourseIdEmptyList() {
		when(mockEntityManager.find(Course.class, 1)).thenReturn(english);
		List<StudentRegistration> emptyList = 
				new ArrayList<StudentRegistration>();
		when(mockStudentRepository.findByCourse(english)).thenReturn(emptyList);
		
		assertEquals(studentDAOimpl.findRegistrationByCourseId(1), emptyList);
	}
	
	@Test
	void testGetStudentRegistrationHappyDay() {
		when(mockStudentRepository.findById(1)).thenReturn(Optional.of(pablo));
		
		assertEquals(studentDAOimpl.getStudentRegistration(1), pablo);
	}
	
	@Test
	void testGetStudentRegistrationNotPresent() {
		when(mockStudentRepository.findById(1)).thenReturn(Optional.empty());
		
		assertEquals(studentDAOimpl.getStudentRegistration(1), null);
	}

}