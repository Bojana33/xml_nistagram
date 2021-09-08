package post.postservice.Controller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class ImageUploaderController {

    @RequestMapping(value = "getimage/{imageUrl1}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ByteArrayResource> getImage(@PathVariable("imageUrl1") String imageUrl1){
        if(imageUrl1.equals("") || imageUrl1 != null) {
            try {
                Path filename = Paths.get("/media/boris/Faks/FTN8/XWS/xml_nistagram/nistagram_microservices/post-service/uploads/" , imageUrl1);
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
