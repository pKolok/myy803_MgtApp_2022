package myy803.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myy803.model.Instructor;
import myy803.repository.InstructorRepository;

@Service
public class InstructorServiceImpl implements InstructorService {
	
	@Autowired
	private InstructorRepository instructorRepository;
	
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
		Instructor instructor = instructorRepository.getInstructor(username);
		return instructor.getPassword();
	}

	@Override
	public void registerInstructor(Instructor instructor) {
		instructorRepository.save(instructor);
	}

}
