package myy803.MgtApp.controller;

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
import org.springframework.ui.Model;

import myy803.controller.StatisticsController;
import myy803.model.Course;
import myy803.model.Instructor;
import myy803.model.StudentRegistration;
import myy803.service.StudentRegistrationService;

class StatisticsControllerTest {

	private static StudentRegistrationService mockStudentService;
	private static Model mockModel;
	private static Instructor maria;
	private static Course english;
	private static StudentRegistration pablo, iman, george, andre;
	private static List<StudentRegistration> students;
	private static StatisticsController statisticsController;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		// Set up mocks
		mockStudentService  = mock(StudentRegistrationService.class);
		mockModel = mock(Model.class);
		
		// Set up courses
		english = new Course("myy101", "English", "Introduction to English", 
				maria, 1, 1, 0.5);
		
		// Set up students
		students = new ArrayList<StudentRegistration>();
		pablo = new StudentRegistration("pablo", 4, 8, english, 2018, 5.0, 8.0,
				6.0);
		iman = new StudentRegistration("iman", 3, 6, english, 2019, 5.0, 7.0,
				6.5);
		george = new StudentRegistration("george", 5, 10, english, 2017, 8.0,
				9.0, 7.5);
		andre = new StudentRegistration("andre", 2, 4, english, 2020, 10.0, 8.0,
				9.5);
		students.add(pablo); students.add(iman); students.add(george);
		students.add(andre);
		
		// Set up class under test
		statisticsController = new StatisticsController(mockStudentService);
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
	void testGoBackToRegistrations() {
		when(mockStudentService.findRegistrationByCourseId(1))
			.thenReturn(students);
		
		assertEquals(statisticsController.goBackToRegistrations("Maria", 1,
				mockModel), "Registrations");
	}

}