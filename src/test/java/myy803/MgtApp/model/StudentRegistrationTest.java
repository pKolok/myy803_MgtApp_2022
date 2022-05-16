package myy803.MgtApp.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import myy803.model.Course;
import myy803.model.StudentRegistration;

class StudentRegistrationTest {
	
	private static StudentRegistration studentRegistration;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		studentRegistration = new StudentRegistration();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {}

	@BeforeEach
	void setUp() throws Exception {}

	@AfterEach
	void tearDown() throws Exception {}

	@Test
	void testSetAndGetIdHappyDay() {
		int number = 10;
		studentRegistration.setId(number);
		assertEquals(studentRegistration.getId(), number);
	}
	
	@Test
	void testSetAndGetNamedHappyDay() {
		String str = "Petros";
		studentRegistration.setName(str);
		assertEquals(studentRegistration.getName(), str);
	}
	
	@Test
	void testSetAndGetNamedEmptyString() {
		String str = "";
		studentRegistration.setName(str);
		assertEquals(studentRegistration.getName(), str);
	}
	
	@Test
	void testSetAndGetYearOfStudiesHappyDay() {
		int number = 4;
		studentRegistration.setYearOfStudies(number);
		assertEquals(studentRegistration.getYearOfStudies(), number);
	}
	
	@Test
	void testSetAndGetYearOfStudiesLarge() {
		int number = 150;
		studentRegistration.setYearOfStudies(number);
		assertEquals(studentRegistration.getYearOfStudies(), number);
	}
	
	@Test
	void testSetAndGetYearOfStudiesZero() {
		int number = 0;
		studentRegistration.setYearOfStudies(number);
		assertEquals(studentRegistration.getYearOfStudies(), number);
	}
	
	@Test
	void testSetAndGetYearOfStudiesNegative() {
		int number = -80;
		studentRegistration.setYearOfStudies(number);
		assertEquals(studentRegistration.getYearOfStudies(), number);
	}
	
	@Test
	void testSetAndGetCourseHappyDay() {
		Course course = new Course();
		studentRegistration.setCourse(course);
		assertEquals(studentRegistration.getCourse(), course);
	}
	
	@Test
	void testSetAndGetCourseNull() {
		Course course = null;
		studentRegistration.setCourse(course);
		assertNull(studentRegistration.getCourse());
	}
	
	@Test
	void testSetAndGetSemesterHappyDay() {
		int number = 9;
		studentRegistration.setSemester(number);
		assertEquals(studentRegistration.getSemester(), number);
	}
	
	@Test
	void testSetAndGetSemesterLarge() {
		int number = 900;
		studentRegistration.setSemester(number);
		assertEquals(studentRegistration.getSemester(), number);
	}
	
	@Test
	void testSetAndGetSemesterZero() {
		int number = 0;
		studentRegistration.setSemester(number);
		assertEquals(studentRegistration.getSemester(), number);
	}
	
	void testSetAndGetSemesterNegative() {
		int number = -7;
		studentRegistration.setSemester(number);
		assertEquals(studentRegistration.getSemester(), number);
	}
	
	@Test
	void testSetAndGetYearOfRegistrationHappyDay() {
		int number = 2018;
		studentRegistration.setYearOfRegistration(number);
		assertEquals(studentRegistration.getYearOfRegistration(), number);
	}
	
	@Test
	void testSetAndGetYearOfRegistrationZero() {
		int number = 0;
		studentRegistration.setYearOfRegistration(number);
		assertEquals(studentRegistration.getYearOfRegistration(), number);
	}
	
	@Test
	void testSetAndGetYearOfRegistrationNegative() {
		int number = -3;
		studentRegistration.setYearOfRegistration(number);
		assertEquals(studentRegistration.getYearOfRegistration(), number);
	}
	
	@Test
	void testSetAndGetProjectGradeHappyDay() {
		double number = 4.7;
		studentRegistration.setProjectGrade(number);
		assertEquals(studentRegistration.getProjectGrade(), number);
	}
	
	@Test
	void testSetAndGetProjectGradeLarge() {
		double number = 150.6;
		studentRegistration.setProjectGrade(number);
		assertEquals(studentRegistration.getProjectGrade(), number);
	}
	
	@Test
	void testSetAndGetProjectGradeZero() {
		double number = 0.0;
		studentRegistration.setProjectGrade(number);
		assertEquals(studentRegistration.getProjectGrade(), number);
	}
	
	@Test
	void testSetAndGetProjectGradeNegative() {
		double number = -4.0;
		studentRegistration.setProjectGrade(number);
		assertEquals(studentRegistration.getProjectGrade(), number);
	}
	
	@Test
	void testSetAndGetExamGradeHappyDay() {
		double number = 7.1;
		studentRegistration.setExamGrade(number);
		assertEquals(studentRegistration.getExamGrade(), number);
	}
	
	@Test
	void testSetAndGetExamGradeLarge() {
		double number = 700.15;
		studentRegistration.setExamGrade(number);
		assertEquals(studentRegistration.getExamGrade(), number);
	}
	
	@Test
	void testSetAndGetExamGradeZero() {
		double number = 0.0;
		studentRegistration.setExamGrade(number);
		assertEquals(studentRegistration.getExamGrade(), number);
	}
	
	@Test
	void testSetAndGetExamGradeNegative() {
		double number = -9.7;
		studentRegistration.setExamGrade(number);
		assertEquals(studentRegistration.getExamGrade(), number);
	}
	
	@Test
	void testSetAndGetGradeHappyDay() {
		double number = 10.0;
		studentRegistration.setGrade(number);
		assertEquals(studentRegistration.getGrade(), number);
	}
	
	@Test
	void testSetAndGetGradeLarge() {
		double number = 1000.0;
		studentRegistration.setGrade(number);
		assertEquals(studentRegistration.getGrade(), number);
	}
	
	@Test
	void testSetAndGetGradeZero() {
		double number = 0.0;
		studentRegistration.setGrade(number);
		assertEquals(studentRegistration.getGrade(), number);
	}
	
	@Test
	void testSetAndGetGradeNegative() {
		double number = -9.0;
		studentRegistration.setGrade(number);
		assertEquals(studentRegistration.getGrade(), number);
	}
	
}