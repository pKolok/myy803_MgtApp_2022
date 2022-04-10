package myy803.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import myy803.model.Course;
import myy803.model.Instructor;

// This will be AUTO IMPLEMENTED by Spring into a Bean called courseRepository
// CRUD refers Create, Read, Update, Delete

@Repository
public interface CourseRepository extends CrudRepository<Course, Integer> {
	
	public List<Course> findByInstructor(Instructor instructor);
}