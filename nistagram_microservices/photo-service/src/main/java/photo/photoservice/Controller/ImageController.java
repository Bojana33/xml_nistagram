package photo.photoservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.ConditionalOnDefaultWebSecurity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import photo.photoservice.Service.ImageService;

@RestController
public class ImageController {
    @Autowired
    private ImageService imageService;


}
