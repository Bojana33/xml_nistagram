package user.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class UserServiceApplication {

	@GetMapping("/getUsers")
	public String getUserService() {
		return "User microservice called...";
	}

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

}
