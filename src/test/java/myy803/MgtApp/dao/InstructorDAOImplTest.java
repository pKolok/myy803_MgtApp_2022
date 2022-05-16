package myy803.MgtApp.dao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import myy803.dao.InstructorDAOImpl;
import myy803.model.Instructor;
import myy803.repository.InstructorRepository;

class InstructorDAOImplTest {

	private static InstructorRepository mockInstructorRepository;
	private static Instructor maria;
	private static InstructorDAOImpl instructorDAOImpl;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		// Set up mocks
		mockInstructorRepository  = mock(InstructorRepository.class);
		
		// Set up instructors
		maria = new Instructor("Maria", String.valueOf("12345".hashCode()));
		
		// Set up class under test
		instructorDAOImpl = new InstructorDAOImpl(mockInstructorRepository);
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
		when(mockInstructorRepository.findById("Maria"))
		.thenReturn(Optional.of(maria));
		
		assertEquals(instructorDAOImpl.existsInstructor("Maria"),
				true);
	}
	
	@Test
	void testExistsInstructorNotPresent() {
		when(mockInstructorRepository.findById("Maria"))
		.thenReturn(Optional.empty());
		
		assertEquals(instructorDAOImpl.existsInstructor("Maria"),
				false);
	}

	@Test
	void testGetInstructorPasswordHappyDay() {
		when(mockInstructorRepository.findById("Maria"))
		.thenReturn(Optional.of(maria));
		
		assertEquals(instructorDAOImpl.getInstructorPassword("Maria"),
				String.valueOf("12345".hashCode()));
	}
	
	@Test
	void testGetInstructorPasswordNotPresent() {
		when(mockInstructorRepository.findById("Maria"))
		.thenReturn(Optional.empty());
		
		assertEquals(instructorDAOImpl.getInstructorPassword("Maria"),
				"");
	}
	
	@Test
	void testGetInstructorHappyDay() {
		when(mockInstructorRepository.findById("Maria"))
		.thenReturn(Optional.of(maria));
		
		assertEquals(instructorDAOImpl.getInstructor("Maria"),
				maria);
	}
	
	@Test
	void testGetInstructorNotPresent() {
		when(mockInstructorRepository.findById("Maria"))
		.thenReturn(Optional.empty());
		
		assertEquals(instructorDAOImpl.getInstructor("Maria"),
				null);
	}
	
}