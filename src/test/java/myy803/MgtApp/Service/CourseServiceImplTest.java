package myy803.MgtApp.Service;

import static org.junit.jupiter.api.Assertions.*;
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

import myy803.dao.CourseDAO;
import myy803.dao.StudentRegistrationDAO;
import myy803.model.Course;
import myy803.model.Instructor;
import myy803.model.StudentRegistration;
import myy803.service.CourseServiceImpl;
import myy803.service.KurtosisStatisticStrategy;
import myy803.service.MaxStatisticStrategy;
import myy803.service.MeanStatisticStrategy;
import myy803.service.MedianStatisticStrategy;
import myy803.service.MinStatisticStrategy;
import myy803.service.NinetyFifthPercentileStatisticStrategy;
import myy803.service.SkewnessStatisticStrategy;
import myy803.service.StandardDeviationStatisticStrategy;
import myy803.service.StatisticStrategy;
import myy803.service.VarianceStatisticStrategy;

class CourseServiceImplTest {

	private static CourseDAO mockCourseDAO;
	private static StudentRegistrationDAO mockStudentRegistrationDAO;
	private static CourseServiceImpl courseServiceImpl;
	private static Instructor maria, helen, julia;
	private static Course english, calculus, physics;
	private static StudentRegistration pablo, iman, george, andre;
	private static List<Course> courses;
	private static List<StudentRegistration> students;
	private static List<StatisticStrategy> stats;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
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
		
		// Set up statistic strategies
		stats = new ArrayList<StatisticStrategy>();
		
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
		
		// Set up mocks
		mockCourseDAO = mock(CourseDAO.class);
		mockStudentRegistrationDAO  = mock(StudentRegistrationDAO.class);
		
		// Set up class under test
		courseServiceImpl = new CourseServiceImpl(mockCourseDAO,
				mockStudentRegistrationDAO);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		students.add(pablo); students.add(iman); students.add(george);
		students.add(andre);
		
		stats.add(new MinStatisticStrategy());
		stats.add(new MaxStatisticStrategy());
		stats.add(new MeanStatisticStrategy());
		stats.add(new StandardDeviationStatisticStrategy());
		stats.add(new VarianceStatisticStrategy());
		stats.add(new NinetyFifthPercentileStatisticStrategy());
		stats.add(new SkewnessStatisticStrategy());
		stats.add(new KurtosisStatisticStrategy());
		stats.add(new MedianStatisticStrategy());
	}

	@AfterEach
	void tearDown() throws Exception {
		students.clear();
		stats.clear();
	}

	@Test
	void testFindCourseByInstructorLoginHappyDay() {
		when(mockCourseDAO.findCourseByInstructorLogin("maria"))
		.thenReturn(courses);
		
		assertEquals(courseServiceImpl.findCourseByInstructorLogin("maria"),
				courses);
	}
	
	@Test
	void testFindCourseByInstructorLoginEmptyList() {
		List<Course> emptyList = new ArrayList<Course>();
		when(mockCourseDAO.findCourseByInstructorLogin("julia"))
		.thenReturn(emptyList);
		
		assertEquals(courseServiceImpl.findCourseByInstructorLogin("julia"),
				emptyList);
	}
	
	@Test
	void testGetCourseHappyDay() {
		when(mockCourseDAO.getCourse(1)).thenReturn(english);
		when(mockCourseDAO.getCourse(2)).thenReturn(calculus);
		
		assertEquals(courseServiceImpl.getCourse(1), english);
		assertEquals(courseServiceImpl.getCourse(2), calculus);
	}
	
	@Test
	void testGetCourseStatisticsHappyDay() {
		english.setId(1);
		when(mockStudentRegistrationDAO.findRegistrationByCourseId(1))
		.thenReturn(students);
		
		HashMap<String, Double> expectedStats = new HashMap<String, Double>();
		expectedStats.put("Min", 6.0);
		expectedStats.put("Max", 9.5);
		expectedStats.put("Mean", 7.375);
		expectedStats.put("Std. Deviation", 1.548);
		expectedStats.put("Variance", 1.797);
		expectedStats.put("95th Percentile", 9.2);
		expectedStats.put("Skewness", 1.138);
		expectedStats.put("Kurtosis", 0.76);
		expectedStats.put("Median", 7.0);
		
		courseServiceImpl.setStatCalculationStrategies(stats);
		HashMap<String, Double> actualStats = courseServiceImpl
				.getCourseStatistics(english);
		
		assertEquals(expectedStats.get("Min"), actualStats.get("Min"));
		assertEquals(expectedStats.get("Max"), actualStats.get("Max"));
		assertEquals(expectedStats.get("Mean"), actualStats.get("Mean"));
		assertEquals(expectedStats.get("Std. Deviation"),
				actualStats.get("Std. Deviation"), 0.2);
		assertEquals(expectedStats.get("Variance"),
				actualStats.get("Variance"), 0.2);
		assertEquals(expectedStats.get("95th Percentile"),
				actualStats.get("95th Percentile"), 0.4);
		assertEquals(expectedStats.get("Skewness"),
				actualStats.get("Skewness"), 0.2);
		assertEquals(expectedStats.get("Kurtosis"),
				actualStats.get("Kurtosis"), 0.2);
		assertEquals(expectedStats.get("Median"),
				actualStats.get("Median"), 0.2);
	}
	
	@Test
	void testGetCourseStatisticsEmptyList() {
		List<StudentRegistration> students = 
				new ArrayList<StudentRegistration>();
		
		english.setId(1);
		when(mockStudentRegistrationDAO.findRegistrationByCourseId(1))
		.thenReturn(students);
		
		courseServiceImpl.setStatCalculationStrategies(stats);
		HashMap<String, Double> actualStats = courseServiceImpl
				.getCourseStatistics(english);
		
		assertTrue(Double.isNaN(actualStats.get("Min")));
		assertTrue(Double.isNaN(actualStats.get("Max")));
		assertTrue(Double.isNaN(actualStats.get("Mean")));
		assertTrue(Double.isNaN(actualStats.get("Std. Deviation")));
		assertTrue(Double.isNaN(actualStats.get("Variance")));
		assertTrue(Double.isNaN(actualStats.get("95th Percentile")));
		assertTrue(Double.isNaN(actualStats.get("Skewness")));
		assertTrue(Double.isNaN(actualStats.get("Kurtosis")));
		assertTrue(Double.isNaN(actualStats.get("Median")));
	}
	
	@Test
	void testSetAndGetStatCalculationStrategiesHappyDay() {
		courseServiceImpl.setStatCalculationStrategies(stats);
		assertEquals(courseServiceImpl.getStatCalculationStrategies(), stats);
	}
	
	@Test
	void testSetAndGetStatCalculationStrategiesEmptyList() {
		List<StatisticStrategy> stats = new ArrayList<StatisticStrategy>();
		courseServiceImpl.setStatCalculationStrategies(stats);
		assertEquals(courseServiceImpl.getStatCalculationStrategies(), stats);
	}
	
}