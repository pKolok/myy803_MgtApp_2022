package myy803.repository;

import org.springframework.data.repository.CrudRepository;

import myy803.model.Course;

// This will be AUTO IMPLEMENTED by Spring into a Bean called courseRepository
// CRUD refers Create, Read, Update, Delete

public interface CourseRepository extends CrudRepository<Course, Integer> {
	
}