package myy803.MgtApp.Controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;

import myy803.controller.StudentRegistrationController;
import myy803.model.Course;
import myy803.model.Instructor;
import myy803.model.StudentRegistration;
import myy803.service.CourseService;
import myy803.service.StudentRegistrationService;

class StudentRegistrationControllerTest {
	
	private static CourseService mockCourseService;
	private static StudentRegistrationService mockStudentService;
	private static Model mockModel;
	private static Instructor maria;
	private static Course english, calculus, physics;
	private static StudentRegistration pablo, iman, george, andre;
	private static List<Course> courses;
	private static List<StudentRegistration> students;
	private static StudentRegistrationController studentController;
	private static HashMap<String, Double> statResults;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		// Set up mocks
		mockCourseService = mock(CourseService.class);
		mockStudentService = mock(StudentRegistrationService.class);
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
		
		// Set up stats
		statResults = new HashMap<String, Double>();
		
		studentController = new StudentRegistrationController(mockCourseService,
				mockStudentService);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		students.add(pablo); students.add(iman); students.add(george);
		students.add(andre);
		
		statResults.put("Mean", 6.0);
		statResults.put("Min", 1.0);
		statResults.put("Max", 9.0);
		statResults.put("Std. Deviation", 4.0);
		statResults.put("Variance", 3.2);
		statResults.put("95th Percentile", 7.0);
		statResults.put("Skewness", 2.1);
		statResults.put("Kurtosis", 1.0);
		statResults.put("Median", 5.1);
	}

	@AfterEach
	void tearDown() throws Exception {
		students.clear();
		statResults.clear();
	}
	

	@Test
	void testShowAddStudentForm() {	
		assertEquals(studentController.showAddStudentForm("Maria", 1, 
				mockModel), "NewStudentForm");
	}

	@Test
	void testAddNewStudentRegistrationHappyDay() {
		when(mockCourseService.getCourse(1)).thenReturn(english);
		when(mockStudentService.findRegistrationByCourseId(1))
		.thenReturn(students);
		
		assertEquals(studentController.addNewStudentRegistration("Maria", 1, 
				"Manu", "4", "8", "2018", "6.9", "7.5", mockModel),
				"Registrations");
	}
	
	@Test
	void testAddNewStudentRegistrationIsBlank() {
		when(mockCourseService.getCourse(1)).thenReturn(english);
		when(mockStudentService.findRegistrationByCourseId(1))
		.thenReturn(students);
		
		assertEquals(studentController.addNewStudentRegistration("Maria", 1, 
				"", "4", "8", "2018", "6.9", "7.5", mockModel),
				"NewStudentForm");
		assertEquals(studentController.addNewStudentRegistration("Maria", 1, 
				"Manu", "", "8", "2018", "6.9", "7.5", mockModel),
				"NewStudentForm");
		assertEquals(studentController.addNewStudentRegistration("Maria", 1, 
				"Manu", "4", "", "2018", "6.9", "7.5", mockModel),
				"NewStudentForm");
		assertEquals(studentController.addNewStudentRegistration("Maria", 1, 
				"Manu", "4", "8", "", "6.9", "7.5", mockModel),
				"NewStudentForm");
		assertEquals(studentController.addNewStudentRegistration("Maria", 1, 
				"Manu", "4", "8", "2018", "", "7.5", mockModel),
				"NewStudentForm");
		assertEquals(studentController.addNewStudentRegistration("Maria", 1, 
				"Manu", "4", "8", "2018", "6.9", "", mockModel),
				"NewStudentForm");
	}
	
	@Test
	void testAddNewStudentRegistrationInvalidYearOfStudies() {
		when(mockCourseService.getCourse(1)).thenReturn(english);
		when(mockStudentService.findRegistrationByCourseId(1))
		.thenReturn(students);
		
		assertEquals(studentController.addNewStudentRegistration("Maria", 1, 
				"Manu", "y4", "8", "2018", "6.9", "7.5", mockModel),
				"NewStudentForm");
		assertEquals(studentController.addNewStudentRegistration("Maria", 1, 
				"Manu", "-4", "8", "2018", "6.9", "7.5", mockModel),
				"NewStudentForm");
	}
	
	@Test
	void testAddNewStudentRegistrationInvalidSemester() {
		when(mockCourseService.getCourse(1)).thenReturn(english);
		when(mockStudentService.findRegistrationByCourseId(1))
		.thenReturn(students);
		
		assertEquals(studentController.addNewStudentRegistration("Maria", 1, 
				"Manu", "4", "y8", "2018", "6.9", "7.5", mockModel),
				"NewStudentForm");
		assertEquals(studentController.addNewStudentRegistration("Maria", 1, 
				"Manu", "4", "-8", "2018", "6.9", "7.5", mockModel),
				"NewStudentForm");
	}
	
	@Test
	void testAddNewStudentRegistrationInvalidYearOfRegistration() {
		when(mockCourseService.getCourse(1)).thenReturn(english);
		when(mockStudentService.findRegistrationByCourseId(1))
		.thenReturn(students);
		
		assertEquals(studentController.addNewStudentRegistration("Maria", 1, 
				"Manu", "4", "8", "y2018", "6.9", "7.5", mockModel),
				"NewStudentForm");
		assertEquals(studentController.addNewStudentRegistration("Maria", 1, 
				"Manu", "4", "8", "-2018", "6.9", "7.5", mockModel),
				"NewStudentForm");
		assertEquals(studentController.addNewStudentRegistration("Maria", 1, 
				"Manu", "4", "8", "2009", "6.9", "7.5", mockModel),
				"NewStudentForm");
	}
	
	@Test
	void testAddNewStudentRegistrationInvalidProjectGrade() {
		when(mockCourseService.getCourse(1)).thenReturn(english);
		when(mockStudentService.findRegistrationByCourseId(1))
		.thenReturn(students);
		
		assertEquals(studentController.addNewStudentRegistration("Maria", 1, 
				"Manu", "4", "8", "2018", "p6.9", "7.5", mockModel),
				"NewStudentForm");
		assertEquals(studentController.addNewStudentRegistration("Maria", 1, 
				"Manu", "4", "8", "2018", "-6.9", "7.5", mockModel),
				"NewStudentForm");
	}
	
	@Test
	void testAddNewStudentRegistrationInvalidExamGrade() {
		when(mockCourseService.getCourse(1)).thenReturn(english);
		when(mockStudentService.findRegistrationByCourseId(1))
		.thenReturn(students);
		
		assertEquals(studentController.addNewStudentRegistration("Maria", 1, 
				"Manu", "4", "8", "2018", "6.9", "e7.5", mockModel),
				"NewStudentForm");
		assertEquals(studentController.addNewStudentRegistration("Maria", 1, 
				"Manu", "4", "8", "2018", "6.9", "-7.5", mockModel),
				"NewStudentForm");
	}

	@Test
	void testCancelAddNewStudentHappyDay() {
		when(mockStudentService.findRegistrationByCourseId(1))
		.thenReturn(students);
		
		assertEquals(studentController.cancelAddNewStudent("Maria", 1, 
				mockModel), "Registrations");
	}
	
	@Test
	void testShowEditStudentFormHappyDay() {
		when(mockStudentService.getStudentRegistration(1))
		.thenReturn(pablo);
		
		assertEquals(studentController.showEditStudentForm("Maria", 1, 1,
				mockModel), "EditStudentForm");
	}
	
	@Test
	void testEditStudentRegistrationHappyDay() {
		when(mockStudentService.getStudentRegistration(1)).thenReturn(pablo);
		when(mockStudentService.findRegistrationByCourseId(1))
		.thenReturn(students);
		
		assertEquals(studentController.editStudent("Maria", 1, 1, 
				"Manu", "4", "8", "2018", "6.9", "7.5", mockModel),
				"Registrations");
	}
	
	@Test
	void testEditStudentRegistrationIsBlank() {
		when(mockCourseService.getCourse(1)).thenReturn(english);
		when(mockStudentService.findRegistrationByCourseId(1))
		.thenReturn(students);
		
		assertEquals(studentController.editStudent("Maria", 1, 1,
				"", "4", "8", "2018", "6.9", "7.5", mockModel),
				"EditStudentForm");
		assertEquals(studentController.editStudent("Maria", 1, 1,
				"Manu", "", "8", "2018", "6.9", "7.5", mockModel),
				"EditStudentForm");
		assertEquals(studentController.editStudent("Maria", 1, 1,
				"Manu", "4", "", "2018", "6.9", "7.5", mockModel),
				"EditStudentForm");
		assertEquals(studentController.editStudent("Maria", 1, 1, 
				"Manu", "4", "8", "", "6.9", "7.5", mockModel),
				"EditStudentForm");
		assertEquals(studentController.editStudent("Maria", 1, 1, 
				"Manu", "4", "8", "2018", "", "7.5", mockModel),
				"EditStudentForm");
		assertEquals(studentController.editStudent("Maria", 1, 1, 
				"Manu", "4", "8", "2018", "6.9", "", mockModel),
				"EditStudentForm");
	}
	
	@Test
	void testEditStudentRegistrationInvalidYearOfStudies() {
		when(mockCourseService.getCourse(1)).thenReturn(english);
		when(mockStudentService.findRegistrationByCourseId(1))
		.thenReturn(students);
		
		assertEquals(studentController.editStudent("Maria", 1, 1,
				"Manu", "y4", "8", "2018", "6.9", "7.5", mockModel),
				"EditStudentForm");
		assertEquals(studentController.editStudent("Maria", 1, 1,
				"Manu", "-4", "8", "2018", "6.9", "7.5", mockModel),
				"EditStudentForm");
	}
	
	@Test
	void testEditStudentRegistrationInvalidSemester() {
		when(mockCourseService.getCourse(1)).thenReturn(english);
		when(mockStudentService.findRegistrationByCourseId(1))
		.thenReturn(students);
		
		assertEquals(studentController.editStudent("Maria", 1, 1,
				"Manu", "4", "y8", "2018", "6.9", "7.5", mockModel),
				"EditStudentForm");
		assertEquals(studentController.editStudent("Maria", 1, 1,
				"Manu", "4", "-8", "2018", "6.9", "7.5", mockModel),
				"EditStudentForm");
	}
	
	@Test
	void testEditStudentRegistrationInvalidYearOfRegistration() {
		when(mockCourseService.getCourse(1)).thenReturn(english);
		when(mockStudentService.findRegistrationByCourseId(1))
		.thenReturn(students);
		
		assertEquals(studentController.editStudent("Maria", 1, 1,
				"Manu", "4", "8", "y2018", "6.9", "7.5", mockModel),
				"EditStudentForm");
		assertEquals(studentController.editStudent("Maria", 1, 1,
				"Manu", "4", "8", "-2018", "6.9", "7.5", mockModel),
				"EditStudentForm");
		assertEquals(studentController.editStudent("Maria", 1, 1,
				"Manu", "4", "8", "2005", "6.9", "7.5", mockModel),
				"EditStudentForm");
	}
	
	@Test
	void testEditStudentRegistrationInvalidProjectGrade() {
		when(mockCourseService.getCourse(1)).thenReturn(english);
		when(mockStudentService.findRegistrationByCourseId(1))
		.thenReturn(students);
		
		assertEquals(studentController.editStudent("Maria", 1, 1,
				"Manu", "4", "8", "2018", "p6.9", "7.5", mockModel),
				"EditStudentForm");
		assertEquals(studentController.editStudent("Maria", 1, 1,
				"Manu", "4", "8", "2018", "-6.9", "7.5", mockModel),
				"EditStudentForm");
	}
	
	@Test
	void testEditStudentRegistrationInvalidExamGrade() {
		when(mockCourseService.getCourse(1)).thenReturn(english);
		when(mockStudentService.findRegistrationByCourseId(1))
		.thenReturn(students);
		
		assertEquals(studentController.editStudent("Maria", 1, 1,
				"Manu", "4", "8", "2018", "6.9", "e7.5", mockModel),
				"EditStudentForm");
		assertEquals(studentController.editStudent("Maria", 1, 1,
				"Manu", "4", "8", "2018", "6.9", "-7.5", mockModel),
				"EditStudentForm");
	}
	
	@Test
	void testCancelStudentEditHappyDay() {
		when(mockStudentService.findRegistrationByCourseId(1))
		.thenReturn(students);
		
		assertEquals(studentController.cancelStudentEdit("Maria", 1, mockModel),
				"Registrations");
	}
	
	@Test
	void testDeleteStudentHappyDay() {
		when(mockStudentService.findRegistrationByCourseId(1))
		.thenReturn(students);
		
		assertEquals(studentController.deleteStudent("Maria", 1, 1, mockModel),
				"Registrations");
	}
	
	@Test
	void testGoBackToCoursesHappyDay() {
		when(mockCourseService.findCourseByInstructorLogin("Maria"))
		.thenReturn(courses);
		
		assertEquals(studentController.goBackToCourses("Maria", mockModel),
				"Courses");
	}
	
	@Test
	void testCalculateFinalGradesHappyDay() {
		when(mockCourseService.getCourse(1)).thenReturn(english);
		when(mockStudentService.findRegistrationByCourseId(1))
		.thenReturn(students);
		
		assertEquals(studentController.calculateFinalGrades("Maria", 1, 
				mockModel), "Registrations");
	}
	
	@Test
	void testCalculateStatsHappyDay() {
		when(mockCourseService.getCourse(1)).thenReturn(english);
		when(mockCourseService.getCourseStatistics(english))
		.thenReturn(statResults);
		
		assertEquals(studentController.calculateStats("Maria", 1, mockModel),
				"CourseStats");
	}
	
	@Test
	void testShowRegistrationsHelpHappyDay() {	
		assertEquals(studentController.showRegistrationsHelp("Maria", 1, 
				mockModel), "RegistrationsHelp");
	}
	
	@Test
	void testBackFromRegistrationsHelpHappyDay() {	
		when(mockStudentService.findRegistrationByCourseId(1))
		.thenReturn(students);
		
		assertEquals(studentController.backFromRegistrationsHelp("Maria", 1, 
				mockModel), "Registrations");
	}
	
}