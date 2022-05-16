package myy803.MgtApp.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import myy803.model.Instructor;

class InstructorTest {
	
	private static Instructor instructor;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		instructor = new Instructor();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {}

	@BeforeEach
	void setUp() throws Exception {}

	@AfterEach
	void tearDown() throws Exception {}

	@Test
	void testSetAndGetUsernameHappyDay() {
		String str = "Teacher";
		instructor.setUsername(str);
		assertEquals(instructor.getUsername(), str);
	}
	
	@Test
	void testSetAndGetUsernameEmptyString() {
		String str = "";
		instructor.setUsername(str);
		assertEquals(instructor.getUsername(), str);
	}
	
	@Test
	void testSetAndGetPasswordHappyDay() {
		String str = "sadsady3423";
		instructor.setPassword(str);
		assertEquals(instructor.getPassword(), str);
	}
	
	@Test
	void testSetAndGetPasswordEmptyString() {
		String str = "";
		instructor.setPassword(str);
		assertEquals(instructor.getPassword(), str);
	}
	
}