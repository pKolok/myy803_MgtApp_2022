package myy803.MgtApp.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;

import myy803.controller.RegisterController;
import myy803.service.InstructorService;

class RegisterControllerTest {

	private static InstructorService mockInstructorService;
	private static Model mockModel;
	private static RegisterController registerController;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		// Set up mocks
		mockInstructorService  = mock(InstructorService.class);
		mockModel = mock(Model.class);
		
		// Set up class under test
		registerController = new RegisterController(mockInstructorService);
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
	void testRegisterInstructorHappyDay() {
		when(mockInstructorService.existsInstructor("Julia")).thenReturn(false);
		
		assertEquals(registerController.registerInstructor("Julia", "123",
				"123", mockModel), "Index");
	}

	@Test
	void testRegisterInstructorInstructorExists() {
		when(mockInstructorService.existsInstructor("Julia")).thenReturn(true);
		
		assertEquals(registerController.registerInstructor("Julia", "123",
				"123", mockModel), "Register");
	}
	
	@Test
	void testRegisterInstructorEmptyPassword() {
		when(mockInstructorService.existsInstructor("Julia")).thenReturn(false);
		
		assertEquals(registerController.registerInstructor("Julia", "",
				"123", mockModel), "Register");
	}
	
	@Test
	void testRegisterInstructorPasswordMismatch() {
		when(mockInstructorService.existsInstructor("Julia")).thenReturn(false);
		
		assertEquals(registerController.registerInstructor("Julia", "123",
				"1234", mockModel), "Register");
	}
	
	@Test
	void testCancelRegisterHappyDay() {
		when(mockInstructorService.existsInstructor("Julia")).thenReturn(false);
		
		assertEquals(registerController.cancelRegister(mockModel), "Index");
	}
	
}