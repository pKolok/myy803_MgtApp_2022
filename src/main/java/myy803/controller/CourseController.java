package myy803.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import myy803.model.Course;
import myy803.repository.CourseRepository;

@RestController
@RequestMapping(path="MgtApp/courses")
public class CourseController {
	
	@Autowired 
	private CourseRepository courseRepository;

	/* either type on browser: 
	 * localhost:8080/MgtApp/courses/all
	 * or type on cmd:
	 * curl localhost:8080/MgtApp/courses/all */
	
	@GetMapping(path="/all")
	public Iterable<Course> getAllCourses() {
		return courseRepository.findAll();
	}
	
	/* in cmd:
	 * curl localhost:8080/MgtApp/courses/add -d name=503 -d description=mpla -d syllabus=mpla -d instructor=teacher -d year=2020 -d semester=5 */
	
	@PostMapping(path="/add")
	public String addNewCourse (@RequestParam String name, 
			@RequestParam String description, @RequestParam String syllabus,
			@RequestParam String instructor, @RequestParam int year,
			@RequestParam int semester) {

		courseRepository.save(new Course(name, description, syllabus, 
				instructor, year, semester));
		
//		courseRepository.save(
//				new Course("701", "mpla", "mpla", "teacher", 2022, 7));
//		courseRepository.save(
//				new Course("702", "mpla", "mpla", "teacher", 2022, 7));
//		courseRepository.save(
//				new Course("703", "mpla", "mpla", "teacher", 2022, 7));
//		courseRepository.save(
//				new Course("801", "mpla", "mpla", "teacher", 2022, 8));
//		courseRepository.save(
//				new Course("802", "mpla", "mpla", "teacher", 2022, 8));

		return "Saved";
	}
	
	@PostMapping(path="/load")
	public Iterable<Course> persist(@RequestBody final Course courses) {
		courseRepository.save(courses);
		return courseRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Course> findUserById(@PathVariable(value = "id") int id) {
	    Optional<Course> course = courseRepository.findById(id);

	    if(course.isPresent()) {
	        return ResponseEntity.ok().body(course.get());
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}

}