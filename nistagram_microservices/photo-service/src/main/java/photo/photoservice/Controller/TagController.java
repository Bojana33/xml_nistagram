package photo.photoservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

}
