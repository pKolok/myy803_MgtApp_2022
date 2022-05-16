package myy803.MgtApp.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import myy803.dao.StudentRegistrationDAO;
import myy803.model.Course;
import myy803.model.Instructor;
import myy803.model.StudentRegistration;

class StudentRegistrationServiceImplTest {

	private static StudentRegistrationDAO mockStudentRegistrationDAO;
	private static Instructor maria, helen, julia;
	private static Course english, calculus, physics;
	private static StudentRegistration pablo, iman, george, andre;
	private static List<Course> courses;
	private static List<StudentRegistration> students;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		// Set up mocks
		mockStudentRegistrationDAO  = mock(StudentRegistrationDAO.class);
		
		// Set up instructors
		maria = new Instructor("Maria", String.valueOf("12345".hashCode()));
		helen = new Instructor("Helen", String.valueOf("11111".hashCode()));
		julia = new Instructor("Julia", String.valueOf("99999".hashCode()));
		
		// Set up courses
		english = new Course("myy101", "English", "Introduction to English", 
				maria, 1, 1);
		calculus = new Course("myy102", "Calculus", "Introduction to Calculus", 
				helen, 1, 1);
		physics = new Course("myy103", "Physics", "Introduction to Physics", 
				julia, 1, 1);
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
		students.clear();
	}

	@Test
	void testFindRegistrationByCourseIdHappyDay() {
		when(mockStudentRegistrationDAO.findRegistrationByCourseId(1))
		.thenReturn(students);
		
		assertEquals(mockStudentRegistrationDAO.findRegistrationByCourseId(1),
				students);
	}
	
	@Test
	void testFindRegistrationByCourseIdEmptyList() {
		List<StudentRegistration> emptyList = 
				new ArrayList<StudentRegistration>();
		when(mockStudentRegistrationDAO.findRegistrationByCourseId(1))
		.thenReturn(emptyList);
		
		assertEquals(mockStudentRegistrationDAO.findRegistrationByCourseId(1),
				emptyList);
	}
	
	@Test
	void testGetStudentRegistrationHappyDay() {
		when(mockStudentRegistrationDAO.getStudentRegistration(1))
		.thenReturn(pablo);
		when(mockStudentRegistrationDAO.getStudentRegistration(2))
		.thenReturn(iman);
		
		assertEquals(mockStudentRegistrationDAO.getStudentRegistration(1),
				pablo);
		assertEquals(mockStudentRegistrationDAO.getStudentRegistration(2),
				iman);
	}
	
}
