package post.postservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import post.postservice.DTO.User;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class ImageUploaderController {

//    private UserInfoServiceImpl userInfoService;
//
//    @Autowired ImageUploaderController(UserInfoServiceImpl userInfoService) {this.userInfoService = userInfoService;}

    @RequestMapping(value = "getimage/{imageUrl1}/{username}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ByteArrayResource> getImage(@PathVariable("imageUrl1") String imageUrl1, @PathVariable("id") Long id,
                                                      Model modell) throws  Exception{
//        if (user == null) { throw new Exception("User does not exist."); }
//        modell.addAttribute("user", user);
        if(imageUrl1.equals("") || imageUrl1 != null) {
            try {
                Path filename = Paths.get("C:\\Users\\User\\IdeaProjects\\xml_nistagram\\nistagram_microservices\\post-service\\uploads" , imageUrl1);
                byte[] buffer = Files.readAllBytes(filename);
                ByteArrayResource byteArrayResource = new ByteArrayResource(buffer);
                return ResponseEntity.ok()
                        .contentLength(buffer.length)
                        .contentType(MediaType.parseMediaType("image/jpg"))
                        .body(byteArrayResource);
            } catch (Exception e) {

            }
        }
        return ResponseEntity.badRequest().build();
    }
}
