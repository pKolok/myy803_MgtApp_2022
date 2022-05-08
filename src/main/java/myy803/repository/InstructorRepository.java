package myy803.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import myy803.model.Instructor;

public interface InstructorRepository extends CrudRepository<Instructor, String> {

	// Not necessary - already a CrudRepository method for it.
	@Query("SELECT i FROM Instructor i where i.username = :username")
	Instructor getInstructor(@Param("username") String username);
	
}