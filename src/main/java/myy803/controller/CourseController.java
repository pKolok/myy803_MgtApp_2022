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
import myy803.model.Instructor;
import myy803.model.StudentRegistration;
import myy803.service.CourseService;
import myy803.service.InstructorService;
import myy803.service.StudentRegistrationService;

/* This class should only be aware of the service layer */

@Controller
public class CourseController {
	
	@Autowired
	private InstructorService instructorService;
	
	@Autowired 
	private CourseService courseService;
	
	@Autowired
	private StudentRegistrationService studentService;

	
	public CourseController(InstructorService instructorService, 
			CourseService courseService, 
			StudentRegistrationService studentService) {
		this.instructorService = instructorService;
		this.courseService = courseService;
		this.studentService = studentService;
	}

	/**
	 * Opens an new page (NewCourseForm.html) to fill in information for a new 
	 * course. Passes the instructor through.
	 * @param instructor
	 * @param model
	 * @return
	 */
	@GetMapping(value="/addCourseForm/{instructor}")
	public String showAddCourseForm(@PathVariable String instructor, Model model) {
		model.addAttribute("instructor", instructor);
		return "NewCourseForm";
	}
	
	/**
	 * Execution comes here when the user has provided information on a new 
	 * course and has selected to add it to the list of courses.
	 * @param instructor
	 * @param name
	 * @param description
	 * @param syllabus
	 * @param year
	 * @param semester
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addCourse/{instructor}", 
			method = RequestMethod.POST, params = "AddCourse")
	public String addNewCourse(@PathVariable String instructor,
			@RequestParam String name, @RequestParam String description, 
			@RequestParam String syllabus, @RequestParam String year,
			@RequestParam String semester, @RequestParam String examWeight,
			Model model) {
		
		List<Course> courses = courseService
				.findCourseByInstructorLogin(instructor);
		
		// These are needed regardless of actual destination
		model.addAttribute("courseList", courses);
		model.addAttribute("instructor", instructor);
		
		if (name.isBlank() || description.isBlank() || syllabus.isBlank()
				|| year.isBlank() || semester.isBlank() 
				|| examWeight.isBlank()) {
			model.addAttribute("error", "Please provide all course info");
			return "NewCourseForm";
		} else if (!isInteger(year)) {
			model.addAttribute("error", "Please provide integer year");
			return "NewCourseForm";
		} else if (Integer.parseInt(year) < 0) {
			model.addAttribute("error", "Please provide a positive year");
			return "NewCourseForm";
		} else if (!isInteger(semester)) {
			model.addAttribute("error", "Please provide integer semester");
			return "NewCourseForm";
		} else if (Integer.parseInt(semester) < 0) {
			model.addAttribute("error", "Please provide positive integer "
					+ "semester");
			return "NewCourseForm";
		} else if (!isDouble(examWeight)) {
			model.addAttribute("error", "Please provide decimal exam weight");
			return "NewCourseForm";
		} else if (Double.parseDouble(examWeight) < 0.0 
				|| Double.parseDouble(examWeight) > 1.0) {
			model.addAttribute("error", "Please provide exam weight in [0,1]");
			return "NewCourseForm";
		} else {
			Instructor instructorObj = instructorService.
					getInstructor(instructor);
			courseService.save(new Course(name, description, syllabus, 
					instructorObj, Integer.parseInt(year), 
					Integer.parseInt(semester), 
					Double.parseDouble(examWeight)));
			// Get courses again after save - needs to be here
			courses = courseService.findCourseByInstructorLogin(instructor);
			model.addAttribute("courseList", courses);
			return "Courses";
		}		
	}
	
	/**
	 * Execution comes here when the user presses cancel while being on the page
	 * when they provide information on a new course.
	 * @param instructor
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addCourse/{instructor}", 
			method = RequestMethod.POST, params = "CancelAdd")
	public String cancelAdd(@PathVariable String instructor, Model model) {
		List<Course> courses = courseService
				.findCourseByInstructorLogin(instructor);
		
		model.addAttribute("courseList", courses);
		model.addAttribute("instructor", instructor);
		return "Courses";
	}
	
	/**
	 * Opens the student registrations page for the selected course.
	 * @param instructor
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/studentRegs/{instructor}/{id}", 
			method = RequestMethod.POST)
	public String showStudentRegistrations(@PathVariable String instructor,
			@PathVariable int id, Model model) {
		
		List<StudentRegistration> studentRegistrations = studentService
				.findRegistrationByCourseId(id);
		
		model.addAttribute("instructor", instructor);
		model.addAttribute("courseId", id);
		model.addAttribute("studentsList", studentRegistrations);
		return "Registrations";
	}
	
	/**
	 * Opens an new page (EditCourseForm.html) to update the information for a  
	 * course. Passes the instructor and course id through.
	 * @param instructor
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/editCourseForm/{instructor}/{id}", 
			method = RequestMethod.POST)
	public String showEditCourseForm(@PathVariable String instructor,
			@PathVariable int id, Model model) {
		Course course = courseService.getCourse(id);	
		model.addAttribute("course", course);
		model.addAttribute("instructor", instructor);
		return "EditCourseForm";
	}
	
	/**
	 * Execution comes here when the user has changed the information on a given 
	 * course and has selected to update this information.
	 * @param instructor
	 * @param id
	 * @param name
	 * @param description
	 * @param syllabus
	 * @param year
	 * @param semester
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/editCourse/{instructor}/{id}", 
			method = RequestMethod.POST, params = "EditCourse")
	public String editCourse(@PathVariable String instructor,
			@PathVariable String id, @RequestParam String name, 
			@RequestParam String description, @RequestParam String syllabus, 
			@RequestParam String year, @RequestParam String semester, 
			@RequestParam String examWeight, Model model) {
		
		Course course = courseService.getCourse(Integer.parseInt(id));
		
		model.addAttribute("instructor", instructor);
		
		if (name.isBlank() || description.isBlank() || syllabus.isBlank()
				|| year.isBlank() || semester.isBlank()) {
			model.addAttribute("course", course);
			model.addAttribute("error", "Please provide all course info");
			return "EditCourseForm";
		} else if (!isInteger(year)) {
			model.addAttribute("course", course);
			model.addAttribute("error", "Please provide integer year");
			return "EditCourseForm";
		} else if (Integer.parseInt(year) < 0) {
			model.addAttribute("course", course);
			model.addAttribute("error", "Please provide a positive year");
			return "EditCourseForm";
		} else if (!isInteger(semester)) {
			model.addAttribute("course", course);
			model.addAttribute("error", "Please provide integer semester");
			return "EditCourseForm";
		} else if (Integer.parseInt(semester) < 0) {
			model.addAttribute("course", course);
			model.addAttribute("error", "Please provide positive integer "
					+ "semester");
			return "EditCourseForm";
		} else if (!isDouble(examWeight)) {
			model.addAttribute("course", course);
			model.addAttribute("error", "Please provide decimal exam weight");
			return "EditCourseForm";
		} else if (Double.parseDouble(examWeight) < 0.0 
				|| Double.parseDouble(examWeight) > 1.0) {
			model.addAttribute("course", course);
			model.addAttribute("error", "Please provide exam weight in [0,1]");
			return "EditCourseForm";
		} else {
			course.setName(name);
			course.setDescription(description);
			course.setSyllabus(syllabus);
			course.setYear(Integer.parseInt(year));
			course.setSemester(Integer.parseInt(semester));
			course.setExamWeight(Double.parseDouble(examWeight));
			
			courseService.update(course);
			
			List<Course> courses = courseService.findCourseByInstructorLogin(instructor);
			model.addAttribute("courseList", courses);
			return "Courses";
		}		
	}
	
	/**
	 * Execution comes here when the user presses cancel while being on the page
	 * when they update the information on a course.
	 * @param instructor
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/editCourse/{instructor}/{id}", 
			method = RequestMethod.POST, params = "CancelCourseEdit")
	public String cancelCourseEdit(@PathVariable String instructor, 
			Model model) {
		
		List<Course> courses = courseService
				.findCourseByInstructorLogin(instructor);
		
		model.addAttribute("courseList", courses);
		model.addAttribute("instructor", instructor);
		return "Courses";
	}
	
	@RequestMapping(value = "/deleteCourse/{instructor}/{id}", 
			method = RequestMethod.POST)
	public String deleteCourse(@PathVariable String instructor, 
			@PathVariable int id, Model model) {
		
		courseService.delete(id);
		
		List<Course> courses = courseService
				.findCourseByInstructorLogin(instructor);
		
		model.addAttribute("courseList", courses);
		model.addAttribute("instructor", instructor);
		return "Courses";
	}
	
	@GetMapping(value="/backToLogin")
	public String logOut() {
		return "Index";
	}
	
	@GetMapping(value="helpWithCourses/{instructor}")
	public String showCourseHelp(@PathVariable String instructor, Model model) {
		model.addAttribute("instructor", instructor);
		return "CoursesHelp";
	}
	
	@GetMapping(value="backFromSourcesHelp/{instructor}")
	public String goBackFromHelp(@PathVariable String instructor, Model model) {
		
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