package myy803.MgtApp.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import myy803.dao.InstructorDAO;
import myy803.model.Instructor;
import myy803.service.InstructorServiceImpl;

class InstructorServiceImplTest {

	private static InstructorDAO mockInstructorDAO;
	private static InstructorServiceImpl instructorServiceImpl;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		mockInstructorDAO = mock(InstructorDAO.class);
		instructorServiceImpl = new InstructorServiceImpl(mockInstructorDAO);
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
	void testExistsInstructorHappyDay() {
		when(mockInstructorDAO.existsInstructor("Pablo")).thenReturn(true);
		when(mockInstructorDAO.existsInstructor("George")).thenReturn(false);
		
		assertEquals(instructorServiceImpl.existsInstructor("Pablo"), true);
		assertEquals(instructorServiceImpl.existsInstructor("George"), false);
	}
	
	@Test
	void testGetInstructorPasswordHappyDay() {
		when(mockInstructorDAO.getInstructorPassword("Pablo"))
		.thenReturn("12345");
		
		assertEquals(instructorServiceImpl.getInstructorPassword("Pablo"),
				"12345");
	}

	@Test
	void testGetInstructorHappyDay() {
		Instructor pablo = new Instructor();
		when(mockInstructorDAO.getInstructor("Pablo"))
		.thenReturn(pablo);
		
		assertEquals(instructorServiceImpl.getInstructor("Pablo"), pablo);
	}
	
}