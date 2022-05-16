package myy803.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myy803.model.Instructor;
import myy803.repository.InstructorRepository;

@Service
public class InstructorDAOImpl implements InstructorDAO {

	@Autowired
	private InstructorRepository instructorRepository;
	
	public InstructorDAOImpl(InstructorRepository instructorRepository) {
		this.instructorRepository = instructorRepository;
	}
	
	@Override
	public boolean existsInstructor(String username) {
		Optional<Instructor> optionalUser = instructorRepository
				.findById(username);
		
		// i.e. optionalUser is not null
		if (optionalUser.isPresent())
			return true;
		
		return false;
	}
	
	@Override
	public String getInstructorPassword(String username) {

		Optional<Instructor> optionalInstructor = instructorRepository
				.findById(username);
		
		if (optionalInstructor.isPresent()) {
			Instructor instructor = optionalInstructor.get();
			return instructor.getPassword();
		}
		return "";
	}

	@Override
	public void registerInstructor(Instructor instructor) {
		instructorRepository.save(instructor);
	}

	@Override
	public Instructor getInstructor(String username) {
		
		Optional<Instructor> optionalInstructor = instructorRepository
				.findById(username);
		
		if (optionalInstructor.isPresent()) {
			Instructor instructor = optionalInstructor.get();
			return instructor;
		}
		return null;
	}

}