package notification.notificationservice.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping(path = "/feign")
    public ResponseEntity<String> test() {
        return new ResponseEntity<String>("jesi primio?", HttpStatus.OK);
    }
}
