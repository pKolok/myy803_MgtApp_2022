package myy803.MgtApp.Controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;

import myy803.controller.LoginController;
import myy803.model.Course;
import myy803.model.Instructor;
import myy803.service.CourseService;
import myy803.service.InstructorService;

class LoginControllerTest {

	private static InstructorService mockInstructorService;
	private static CourseService mockCourseService;
	private static Model mockModel;
	private static Instructor maria;
	private static Course english, calculus, physics;
	private static List<Course> courses;
	private static LoginController loginController;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		// Set up mocks
		mockInstructorService  = mock(InstructorService.class);
		mockCourseService = mock(CourseService.class);
		mockModel = mock(Model.class);
		
		// Set up instructors
		maria = new Instructor("Maria", String.valueOf("12345".hashCode()));
		
		// Set up courses
		english = new Course("myy101", "English", "Introduction to English", 
				maria, 1, 1);
		calculus = new Course("myy102", "Calculus", "Introduction to Calculus", 
				maria, 1, 1);
		physics = new Course("myy103", "Physics", "Introduction to Physics", 
				maria, 1, 1);
		courses = new ArrayList<Course>();
		courses.add(english); courses.add(calculus); courses.add(physics);
		
		// Set up class under test
		loginController = new LoginController(mockInstructorService,
				mockCourseService);
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
	void testLoginHappyDay() {
		when(mockInstructorService.existsInstructor("Maria")).thenReturn(true);
		when(mockInstructorService.getInstructorPassword("Maria"))
		.thenReturn(maria.getPassword());
		
		when(mockCourseService.findCourseByInstructorLogin("Maria"))
		.thenReturn(courses);
		
		assertEquals(loginController.login(maria.getUsername(), 
				"12345", mockModel), "Courses");
	}
	
	@Test
	void testLoginBlankUserName() {	
		assertEquals(loginController.login("","", mockModel), "Index");
	}
	
	@Test
	void testLoginNonExistentInstructor() {
		when(mockInstructorService.existsInstructor("Maria")).thenReturn(false);
		
		assertEquals(loginController.login(maria.getUsername(), 
				"12345", mockModel), "Index");
	}
	
	@Test
	void testLoginWrongPassword() {
		when(mockInstructorService.existsInstructor("Maria")).thenReturn(true);
		when(mockInstructorService.getInstructorPassword("Maria"))
		.thenReturn("wrongPassword");
		
		assertEquals(loginController.login(maria.getUsername(), 
				"12345", mockModel), "Index");
	}

	@Test
	void testRegisterHappyDay() {	
		assertEquals(loginController.showRegisterForm(mockModel), "Register");
	}
	
}