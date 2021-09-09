package photo.photoservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import photo.photoservice.Model.Tag;
import photo.photoservice.Service.TagService;

import java.util.List;

@RequestMapping(value = "/tag")
@RestController
public class TagController {

    @Autowired
    private TagService tagService;

    @PostMapping("/findByName")
    public ResponseEntity<?> findByName(@PathVariable("name") String name) {
        List<Tag> tags = tagService.findByName(name);
        return ResponseEntity.ok(tags);
    }

    @GetMapping("/test")
    public ResponseEntity<String> test(){
        System.out.println("test");
        return new ResponseEntity("test",HttpStatus.OK);
    }

}
