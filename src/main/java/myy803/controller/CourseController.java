package myy803.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import myy803.model.Course;
import myy803.model.Instructor;
import myy803.service.CourseService;

/* This class should only be aware of the service layer */

@RestController
@RequestMapping(path="MgtApp/courses")
public class CourseController {
	
	@Autowired 
	private CourseService courseService;
		
	
	@PostMapping(path="/add")
	public String addNewCourse (@RequestParam String name, 
			@RequestParam String description, @RequestParam String syllabus,
			@RequestParam Instructor instructor, @RequestParam int year,
			@RequestParam int semester) {

		courseService.save(new Course(name, description, syllabus, 
				instructor, year, semester));

		return "Saved";
	}
	
}