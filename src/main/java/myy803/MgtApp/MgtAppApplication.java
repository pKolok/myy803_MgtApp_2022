package myy803.MgtApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// to see website: localhost:8080

@SpringBootApplication
@RestController		// describes an end-point that should be made available over the web.
public class MgtAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MgtAppApplication.class, args);
	}

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
	return String.format("Hello %s!", name);
	}
}