package myy803;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

// to see website: localhost:8080

@EnableJpaRepositories(basePackages = "myy803.repository")
@SpringBootApplication
public class MgtAppApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(MgtAppApplication.class, args);
	}

}