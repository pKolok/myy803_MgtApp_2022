package myy803.MgtApp.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import myy803.model.Course;
import myy803.model.Instructor;

class CourseTest {
	
	private static Course course;
	private static Instructor instructor;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		course = new Course();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {}

	@BeforeEach
	void setUp() throws Exception {}

	@AfterEach
	void tearDown() throws Exception {}

	@Test
	void testSetAndGetIdHappyDay() {
		int number = 6;
		course.setId(number);
		assertEquals(course.getId(), number);
	}
	
	@Test
	void testSetAndGetIdZero() {
		int number = 0;
		course.setId(number);
		assertEquals(course.getId(), number);
	}
	
	@Test
	void testSetAndGetIdNegative() {
		int number = -9;
		course.setId(number);
		assertEquals(course.getId(), number);
	}

	@Test
	void testSetAndGetNameHappyDay() {
		String str = "myy601";
		course.setName(str);
		assertEquals(course.getName(), str);
	}
	
	@Test
	void testSetAndGetNameEmptyString() {
		String str = "";
		course.setName(str);
		assertEquals(course.getName(), str);
	}
	
	@Test
	void testSetAndGetDescriptionHappyDay() {
		String str = "OperatingSystems";
		course.setDescription(str);
		assertEquals(course.getDescription(), str);
	}
	
	@Test
	void testSetAndGetDescriptionEmptyString() {
		String str = "";
		course.setDescription(str);
		assertEquals(course.getDescription(), str);
	}
	
	@Test
	void testSetAndGetSyllabusHappyDay() {
		String str = "Basics of Operating Systems";
		course.setSyllabus(str);
		assertEquals(course.getSyllabus(), str);
	}
	
	@Test
	void testSetAndGetSyllabusEmptyString() {
		String str = "";
		course.setSyllabus(str);
		assertEquals(course.getSyllabus(), str);
	}
	
	@Test
	void testSetAndGetInstructorHappyDay() {
		instructor = new Instructor();
		course.setInstructor(instructor);
		assertEquals(course.getInstructor(), instructor);
	}
	
	@Test
	void testSetAndGetInstructorNull() {
		instructor = null;
		course.setInstructor(instructor);
		assertNull(course.getInstructor());
	}
	
	@Test
	void testSetAndGetYearHappyDay() {
		int year = 9;
		course.setYear(year);
		assertEquals(course.getYear(), year);
	}
	
	@Test
	void testSetAndGetYearLarge() {
		int year = 500;
		course.setYear(year);
		assertEquals(course.getYear(), year);
	}
	
	@Test
	void testSetAndGetYearZero() {
		int year = 5;
		course.setYear(year);
		assertEquals(course.getYear(), year);
	}
	
	@Test
	void testSetAndGetYearNegative() {
		int year = -95;
		course.setYear(year);
		assertEquals(course.getYear(), year);
	}
	
	@Test
	void testSetAndGetSemesterHappyDay() {
		int semester = 8;
		course.setSemester(semester);
		assertEquals(course.getSemester(), semester);
	}
	
	@Test
	void testSetAndGetSemesterLarge() {
		int semester = 888;
		course.setSemester(semester);
		assertEquals(course.getSemester(), semester);
	}
	
	@Test
	void testSetAndGetSemesterZero() {
		int semester = 0;
		course.setSemester(semester);
		assertEquals(course.getSemester(), semester);
	}
	
	@Test
	void testSetAndGetSemesterNegative() {
		int semester = -90;
		course.setSemester(semester);
		assertEquals(course.getSemester(), semester);
	}
	
	@Test
	void testSetAndGetExamWeightHappyDay() {
		double weight = 0.6;
		course.setExamWeight(weight);
		assertEquals(course.getExamWeight(), weight);
	}
	
	@Test
	void testSetAndGetExamWeightLarge() {
		double weight = 140.54;
		course.setExamWeight(weight);
		assertEquals(course.getExamWeight(), weight);
	}
	
	@Test
	void testSetAndGetExamWeightZero() {
		double weight = 0;
		course.setExamWeight(weight);
		assertEquals(course.getExamWeight(), weight);
	}
	
	@Test
	void testSetAndGetExamWeightNegative() {
		double weight = -6.0;
		course.setExamWeight(weight);
		assertEquals(course.getExamWeight(), weight);
	}
	
}
