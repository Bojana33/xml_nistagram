package post.postservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import post.postservice.Controller.PostController;

import java.io.File;

@SpringBootApplication
@ComponentScan({"post.postservice","Controller"})
public class PostServiceApplication {

	public static void main(String[] args) {

		new File(PostController.uploadDirectory).mkdir();
		SpringApplication.run(PostServiceApplication.class, args);
	}

}
