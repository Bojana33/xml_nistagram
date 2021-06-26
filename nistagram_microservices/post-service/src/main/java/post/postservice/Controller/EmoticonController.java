package post.postservice.Controller;

import antlr.collections.List;
import antlr.collections.impl.LList;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import post.postservice.Model.EmoticonType;
import post.postservice.Model.Post;
import post.postservice.Service.EmoticonService;

@RestController
public class EmoticonController {

    @Autowired
    private EmoticonService emoticonService;

    @GetMapping("/likes")
    public ResponseEntity<?> findByLike(@PathVariable("emoticonType") EmoticonType emoticonType) throws Exception{
        return new ResponseEntity<>(emoticonService.findByLike(emoticonType), HttpStatus.OK);
    }

    @GetMapping("/dislikes")
    public ResponseEntity<?> findByDislike(@PathVariable("emoticonType") EmoticonType emoticonType) throws Exception{
        return new ResponseEntity<>(emoticonService.findByDislike(emoticonType), HttpStatus.OK);
    }
}
