package post.postservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import post.postservice.Controller.PostController;

import java.io.File;

@SpringBootApplication
@EnableFeignClients
//@ComponentScan({"post.postservice","Controller"})
public class PostServiceApplication {

	public static void main(String[] args) {

		new File(PostController.uploadDirectory).mkdir();
		SpringApplication.run(PostServiceApplication.class, args);
	}

}
