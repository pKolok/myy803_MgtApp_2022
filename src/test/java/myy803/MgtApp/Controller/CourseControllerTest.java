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

import myy803.controller.CourseController;
import myy803.model.Course;
import myy803.model.Instructor;
import myy803.service.CourseService;
import myy803.service.InstructorService;
import myy803.service.StudentRegistrationService;

class CourseControllerTest {

	private static InstructorService mockInstructorService;
	private static CourseService mockCourseService;
	private static StudentRegistrationService mockStudentService;
	private static Model mockModel;
	private static Instructor maria;
	private static Course english, calculus, physics;
	private static List<Course> courses;
	private static CourseController courseController;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		// Set up mocks
		mockInstructorService  = mock(InstructorService.class);
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
		
		// Set up class under test
		courseController = new CourseController(mockInstructorService, 
				mockCourseService, mockStudentService);
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
	void testShowAddCourseForm() {	
		assertEquals(courseController.showAddCourseForm("Maria", mockModel),
				"NewCourseForm");
	}
	
	@Test
	void testAddNewCourseHappyDay() {
		when(mockCourseService.findCourseByInstructorLogin("Maria"))
		.thenReturn(courses);
		when(mockInstructorService.getInstructor("Maria")).thenReturn(maria);
		
		assertEquals(courseController.addNewCourse("Joanne", "myy104", 
				"Linear Algebra", "Intro to LA", "1", "1", "0.6", 
				mockModel), "Courses");
	}
	
	@Test
	void testAddNewCourseIsBlank() {
		when(mockCourseService.findCourseByInstructorLogin("Maria"))
		.thenReturn(courses);
		
		assertEquals(courseController.addNewCourse("Joanne", "", 
				"Linear Algebra", "Intro to LA", "1", "1", "0.6", 
				mockModel), "NewCourseForm");
		assertEquals(courseController.addNewCourse("Joanne", "myy104", 
				"", "Intro to LA", "1", "1", "0.6", 
				mockModel), "NewCourseForm");
		assertEquals(courseController.addNewCourse("Joanne", "myy104", 
				"Linear Algebra", "", "1", "1", "0.6", 
				mockModel), "NewCourseForm");
		assertEquals(courseController.addNewCourse("Joanne", "myy104", 
				"Linear Algebra", "Intro to LA", "", "1", "0.6", 
				mockModel), "NewCourseForm");
		assertEquals(courseController.addNewCourse("Joanne", "myy104", 
				"Linear Algebra", "Intro to LA", "1", "", "0.6", 
				mockModel), "NewCourseForm");
		assertEquals(courseController.addNewCourse("Joanne", "myy104", 
				"Linear Algebra", "Intro to LA", "1", "1", "", 
				mockModel), "NewCourseForm");
	}
	
	@Test
	void testAddNewCourseInvalidYear() {
		when(mockCourseService.findCourseByInstructorLogin("Maria"))
		.thenReturn(courses);
		
		assertEquals(courseController.addNewCourse("Joanne", "myy104", 
				"Linear Algebra", "Intro to LA", "y1", "1", "0.6", 
				mockModel), "NewCourseForm");
		assertEquals(courseController.addNewCourse("Joanne", "myy104", 
				"Linear Algebra", "Intro to LA", "-1", "1", "0.6", 
				mockModel), "NewCourseForm");
	}
	
	@Test
	void testAddNewCourseInvalidSemester() {
		when(mockCourseService.findCourseByInstructorLogin("Maria"))
		.thenReturn(courses);
		
		assertEquals(courseController.addNewCourse("Joanne", "myy104", 
				"Linear Algebra", "Intro to LA", "1", "y1", "0.6", 
				mockModel), "NewCourseForm");
		assertEquals(courseController.addNewCourse("Joanne", "myy104", 
				"Linear Algebra", "Intro to LA", "1", "-1", "0.6", 
				mockModel), "NewCourseForm");
	}
	
	@Test
	void testAddNewCourseInvalidExamWeight() {
		when(mockCourseService.findCourseByInstructorLogin("Maria"))
		.thenReturn(courses);
		
		assertEquals(courseController.addNewCourse("Joanne", "myy104", 
				"Linear Algebra", "Intro to LA", "1", "1", "y0.6", 
				mockModel), "NewCourseForm");
		assertEquals(courseController.addNewCourse("Joanne", "myy104", 
				"Linear Algebra", "Intro to LA", "1", "1", "-0.6", 
				mockModel), "NewCourseForm");
		assertEquals(courseController.addNewCourse("Joanne", "myy104", 
				"Linear Algebra", "Intro to LA", "1", "1", "1.6", 
				mockModel), "NewCourseForm");
	}

	@Test
	void testCancelAddHappyDay() {
		when(mockCourseService.findCourseByInstructorLogin("Maria"))
		.thenReturn(courses);
		
		assertEquals(courseController.cancelAdd("Maria", mockModel), "Courses");
	}
	
	@Test
	void testShowStudentRegistrationsHappyDay() {
		when(mockCourseService.findCourseByInstructorLogin("Maria"))
		.thenReturn(courses);
		
		assertEquals(courseController.showStudentRegistrations("Maria", 1,
				mockModel), "Registrations");
	}
	
	@Test
	void testShowEditCourseFormHappyDay() {
		when(mockCourseService.getCourse(1)).thenReturn(english);
		
		assertEquals(courseController.showEditCourseForm("Maria", 1,
				mockModel), "EditCourseForm");
	}
	
	@Test
	void testEditCourseHappyDay() {
		when(mockCourseService.findCourseByInstructorLogin("Maria"))
		.thenReturn(courses);
		when(mockCourseService.getCourse(1)).thenReturn(english);
		
		assertEquals(courseController.editCourse("Joanne", "1", "myy104", 
				"Linear Algebra", "Intro to LA", "1", "1", "0.6", 
				mockModel), "Courses");
	}
	
	@Test
	void testEdiCourseIsBlank() {
		when(mockCourseService.findCourseByInstructorLogin("Maria"))
		.thenReturn(courses);
		
		assertEquals(courseController.editCourse("Joanne", "1", "", 
				"Linear Algebra", "Intro to LA", "1", "1", "0.6", 
				mockModel), "EditCourseForm");
		assertEquals(courseController.editCourse("Joanne", "1", "myy104", 
				"", "Intro to LA", "1", "1", "0.6", 
				mockModel), "EditCourseForm");
		assertEquals(courseController.editCourse("Joanne", "1", "myy104", 
				"Linear Algebra", "", "1", "1", "0.6", 
				mockModel), "EditCourseForm");
		assertEquals(courseController.editCourse("Joanne", "1", "myy104", 
				"Linear Algebra", "Intro to LA", "", "1", "0.6", 
				mockModel), "EditCourseForm");
		assertEquals(courseController.editCourse("Joanne", "1", "myy104", 
				"Linear Algebra", "Intro to LA", "1", "", "0.6", 
				mockModel), "EditCourseForm");
		assertEquals(courseController.editCourse("Joanne", "1", "myy104", 
				"Linear Algebra", "Intro to LA", "1", "1", "", 
				mockModel), "EditCourseForm");
	}
	
	@Test
	void testEditCourseInvalidYear() {
		when(mockCourseService.findCourseByInstructorLogin("Maria"))
		.thenReturn(courses);
		
		assertEquals(courseController.editCourse("Joanne", "1", "myy104", 
				"Linear Algebra", "Intro to LA", "y1", "1", "0.6", 
				mockModel), "EditCourseForm");
		assertEquals(courseController.editCourse("Joanne", "1", "myy104", 
				"Linear Algebra", "Intro to LA", "-1", "1", "0.6", 
				mockModel), "EditCourseForm");
	}
	
	@Test
	void testEditCourseInvalidSemester() {
		when(mockCourseService.findCourseByInstructorLogin("Maria"))
		.thenReturn(courses);
		
		assertEquals(courseController.editCourse("Joanne", "1", "myy104", 
				"Linear Algebra", "Intro to LA", "1", "y1", "0.6", 
				mockModel), "EditCourseForm");
		assertEquals(courseController.editCourse("Joanne", "1", "myy104", 
				"Linear Algebra", "Intro to LA", "1", "-1", "0.6", 
				mockModel), "EditCourseForm");
	}
	
	@Test
	void testEditCourseInvalidExamWeight() {
		when(mockCourseService.findCourseByInstructorLogin("Maria"))
		.thenReturn(courses);
		
		assertEquals(courseController.editCourse("Joanne", "1", "myy104", 
				"Linear Algebra", "Intro to LA", "1", "1", "y0.6", 
				mockModel), "EditCourseForm");
		assertEquals(courseController.editCourse("Joanne", "1", "myy104", 
				"Linear Algebra", "Intro to LA", "1", "1", "-0.6", 
				mockModel), "EditCourseForm");
		assertEquals(courseController.editCourse("Joanne", "1", "myy104", 
				"Linear Algebra", "Intro to LA", "1", "1", "1.6", 
				mockModel), "EditCourseForm");
	}

	@Test
	void testDeleteCourseHappyDay() {
		when(mockCourseService.findCourseByInstructorLogin("Maria"))
		.thenReturn(courses);
		
		assertEquals(courseController.deleteCourse("Maria", 1, mockModel),
				"Courses");
	}
	
	@Test
	void testCancelCourseEditHappyDay() {
		when(mockCourseService.findCourseByInstructorLogin("Maria"))
		.thenReturn(courses);
		
		assertEquals(courseController.cancelCourseEdit("Maria", mockModel),
				"Courses");
	}
	
	@Test
	void testlogOutHappyDay() {
		assertEquals(courseController.logOut(), "Index");
	}
	
	@Test
	void testShowCourseHelpHappyDay() {
		when(mockCourseService.findCourseByInstructorLogin("Maria"))
		.thenReturn(courses);
		
		assertEquals(courseController.showCourseHelp("Maria", mockModel),
				"CoursesHelp");
	}
	
	@Test
	void testGoBackFromHelpHappyDay() {
		when(mockCourseService.findCourseByInstructorLogin("Maria"))
		.thenReturn(courses);
		
		assertEquals(courseController.goBackFromHelp("Maria", mockModel),
				"Courses");
	}
	
}