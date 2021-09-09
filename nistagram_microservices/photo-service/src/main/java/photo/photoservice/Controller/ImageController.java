package photo.photoservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import photo.photoservice.Model.Image;
import photo.photoservice.Repository.ImageRepository;

import java.time.LocalDate;

@RestController
public class ImageController {

    @Autowired
    public ImageRepository imageRepository;

    public static String uploadDirectory = System.getProperty("images","/uploads");

    @GetMapping("/newImage")
    public Image newImage() {
        Image im = new Image();
        im.setCreatedAt(LocalDate.now());
        im.setFileName("slika1");
        im.setUri("url slike");
        return im;
    }

}
