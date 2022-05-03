package myy803.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import myy803.model.Course;
import myy803.model.StudentRegistration;
import myy803.service.CourseService;
import myy803.service.StudentRegistrationService;

/* This class should only be aware of the service layer */

@Controller
public class StudentRegistrationController {
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private StudentRegistrationService studentService;
	
	/**
	 * Opens an new page (NewStudentForm.html) to fill in information for a new 
	 * student. Passes the instructor and course id through.
	 * @param instructor
	 * @param courseId
	 * @param model
	 * @return
	 */
	@GetMapping(path="/addStudentForm/{instructor}/{courseId}")
	public String addStudentInGUI(@PathVariable String instructor, 
			@PathVariable int courseId, Model model) {
		model.addAttribute("instructor", instructor);
		model.addAttribute("courseId", courseId);
		return "NewStudentForm";
	}
	
	@RequestMapping(value = "/addStudent/{instructor}/{courseId}", 
			method = RequestMethod.POST, params = "AddStudent")
	public String addNewStudentRegistration (@PathVariable String instructor,
			@PathVariable int courseId, @RequestParam String name, 
			@RequestParam String yearOfStudies, @RequestParam String semester, 
			@RequestParam String yearOfRegistration, 
			@RequestParam String projectGrade, @RequestParam String examGrade,
			Model model) {

		// These are needed regardless of actual destination
		model.addAttribute("instructor", instructor);
		model.addAttribute("courseId", courseId);
		
		if (name.isBlank() || yearOfStudies.isBlank() || semester.isBlank()
				|| yearOfRegistration.isBlank() || projectGrade.isBlank()
				|| examGrade.isBlank()) {
			model.addAttribute("error", "Please provide all student info");
			return "NewStudentForm";
		} else if (!isInteger(yearOfStudies)) {
			model.addAttribute("error", "Please provide integer year of "
					+ "studies");
			return "NewStudentForm";
		} else if (!isInteger(semester)) {
			model.addAttribute("error", "Please provide integer semester");
			return "NewStudentForm";
		} else if (!isInteger(yearOfRegistration)) {
			model.addAttribute("error", "Please provide integer year of "
					+ "registration");
			return "NewStudentForm";
		} else if (!isDouble(projectGrade)) {
			model.addAttribute("error", "Please provide decimal project grade");
			return "NewStudentForm";
		} else if (Double.parseDouble(projectGrade) < 0) {
			model.addAttribute("error", "Please provide positive project grade");
			return "NewStudentForm";
		} else if (!isDouble(examGrade)) {
			model.addAttribute("error", "Please provide decimal exam grade");
			return "NewStudentForm";
		} else if (Double.parseDouble(examGrade) < 0) {
			model.addAttribute("error", "Please provide positive exam grade");
			return "NewStudentForm";
		} else {
			Course course = courseService.getCourse(courseId);

			studentService.save(new StudentRegistration(name, 
					Integer.parseInt(yearOfStudies), Integer.parseInt(semester), 
					course, Integer.parseInt(yearOfRegistration), 
					Double.parseDouble(projectGrade), 
					Double.parseDouble(examGrade), 0.0));
			
			List<StudentRegistration> studentRegistrations = studentService
					.findRegistrationByCourseId(courseId);
			
			model.addAttribute("studentsList", studentRegistrations);
			return "Registrations";
		}

	}
	
	/**
	 * Opens an new page (EditStudentForm.html) to update the information for a  
	 * student. Passes the instructor and course id through.
	 * @param instructor
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/editStudentForm/{instructor}/{courseId}/{studentId}", 
			method = RequestMethod.POST)
	public String editStudentInGUI(@PathVariable String instructor,
			@PathVariable int courseId, @PathVariable int studentId, 
			Model model) {
		StudentRegistration student = studentService.getStudentRegistration(
				studentId);	
		model.addAttribute("instructor", instructor);
		model.addAttribute("courseId", courseId);
		model.addAttribute("student", student);
		return "EditStudentForm";
	}

	/**
	 * Execution comes here when the user has changed the information on a given 
	 * student registration and has selected to update this information.
	 * @param instructor
	 * @param courseId
	 * @param studentId
	 * @param name
	 * @param yearOfStudies
	 * @param semester
	 * @param yearOfRegistration
	 * @param projectGrade
	 * @param examGrade
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/editStudent/{instructor}/{courseId}/{studentId}", 
			method = RequestMethod.POST, params = "EditStudent")
	public String editCourse (@PathVariable String instructor,
			@PathVariable int courseId, @PathVariable int studentId, 
			@RequestParam String name, @RequestParam String yearOfStudies,
			@RequestParam String semester, 
			@RequestParam String yearOfRegistration, 
			@RequestParam String projectGrade, @RequestParam String examGrade,  
			Model model) {

		StudentRegistration student = studentService
				.getStudentRegistration(studentId);
		
		// These are needed regardless of actual destination
		model.addAttribute("instructor", instructor);
		model.addAttribute("courseId", courseId);
		
		if (name.isBlank() || yearOfStudies.isBlank() || semester.isBlank()
				|| yearOfRegistration.isBlank() || projectGrade.isBlank()
				|| examGrade.isBlank()) {
			model.addAttribute("student", student);
			model.addAttribute("error", "Please provide all student info");
			return "EditStudentForm";
		} else if (!isInteger(yearOfStudies)) {
			model.addAttribute("student", student);
			model.addAttribute("error", "Please provide integer year of "
					+ "studies");
			return "EditStudentForm";
		} else if (Integer.parseInt(yearOfStudies) < 0) {
			model.addAttribute("student", student);
			model.addAttribute("error", "Please provide positive integer year "
					+ "of studies");
			return "EditStudentForm";
		} else if (!isInteger(semester)) {
			model.addAttribute("student", student);
			model.addAttribute("error", "Please provide integer semester");
			return "EditStudentForm";
		} else if (Integer.parseInt(semester) < 0) {
			model.addAttribute("student", student);
			model.addAttribute("error", "Please provide positive integer "
					+ "semester");
			return "EditStudentForm";
		} else if (!isInteger(yearOfRegistration)) {
			model.addAttribute("student", student);
			model.addAttribute("error", "Please provide integer year of "
					+ "registration");
			return "EditStudentForm";
		} else if (Integer.parseInt(yearOfRegistration) < 2010) {
			model.addAttribute("student", student);
			model.addAttribute("error", "Internal students no longer allowed");
			return "EditStudentForm";
		} else if (!isDouble(projectGrade)) {
			model.addAttribute("student", student);
			model.addAttribute("error", "Please provide decimal project grade");
			return "EditStudentForm";
		} else if (Double.parseDouble(projectGrade) < 0) {
			model.addAttribute("student", student);
			model.addAttribute("error", "Please provide positive project grade");
			return "EditStudentForm";
		} else if (!isDouble(examGrade)) {
			model.addAttribute("student", student);
			model.addAttribute("error", "Please provide decimal exam grade");
			return "EditStudentForm";
		} else if (Double.parseDouble(examGrade) < 0) {
			model.addAttribute("student", student);
			model.addAttribute("error", "Please provide positive exam grade");
			return "EditStudentForm";
		} else {
			student.setName(name);
			student.setYearOfRegistration(Integer.parseInt(yearOfStudies));
			student.setSemester(Integer.parseInt(semester));
			student.setYearOfRegistration(Integer.parseInt(yearOfRegistration));
			student.setProjectGrade(Double.parseDouble(projectGrade));
			student.setExamGrade(Double.parseDouble(examGrade));
			
			studentService.update(student);
			
			List<StudentRegistration> studentRegistrations = studentService
					.findRegistrationByCourseId(courseId);
			
			model.addAttribute("studentsList", studentRegistrations);
			return "Registrations";
		}		
	}
	
	/**
	 * Execution comes here when the user presses cancel while being on the page
	 * when they update the information on a course.
	 * @param instructor
	 * @param courseId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/editStudent/{instructor}/{courseId}/{studentId}", 
			method = RequestMethod.POST, params = "CancelStudentEdit")
	public String cancelStudentEdit(@PathVariable String instructor, 
			@PathVariable int courseId, Model model) {
		
		List<StudentRegistration> studentRegistrations = studentService
				.findRegistrationByCourseId(courseId);
		
		model.addAttribute("instructor", instructor);
		model.addAttribute("courseId", courseId);
		model.addAttribute("studentsList", studentRegistrations);
		return "Registrations";
	}
	
	
	
	@RequestMapping(value = "/deleteStudent/{instructor}/{courseId}/{studentId}", 
			method = RequestMethod.POST)
	public String deleteCourse(@PathVariable String instructor, 
			@PathVariable int courseId, @PathVariable int studentId, 
			Model model) {
		
		studentService.delete(studentId);
		
		List<StudentRegistration> studentRegistrations = studentService
				.findRegistrationByCourseId(courseId);
		
		model.addAttribute("studentsList", studentRegistrations);
		return "Registrations";
	}
	
	/**
	 * When the user presses back on the student registration page, the courses
	 * page is shown.
	 * @param instructor
	 * @param model
	 * @return
	 */
	@GetMapping(value="/backToCourses/{instructor}")
	public String backToCourse(@PathVariable String instructor, Model model) {
		
		List<Course> courses = courseService
				.findCourseByInstructorLogin(instructor);
		model.addAttribute("courseList", courses);
		model.addAttribute("instructor", instructor);
		return "Courses";
	}
  
	
	private boolean isInteger(String str) {
		try {  
			Integer.parseInt(str);
			return true;
		} catch(NumberFormatException e){  
			return false;  
		}  
	}
	
	private boolean isDouble(String str) {
		try {  
			Double.parseDouble(str);
			return true;
		} catch(NumberFormatException e){  
			return false;  
		}  
	}
}