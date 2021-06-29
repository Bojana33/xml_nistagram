package post.postservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import post.postservice.Model.EmoticonType;
import post.postservice.Model.UserInfo;
import post.postservice.Service.EmoticonService;

@RestController
public class EmoticonController {

    @Autowired
    private EmoticonService emoticonService;

    @GetMapping("/likes")
    public ResponseEntity<?> findByLike(@PathVariable("emoticonType") EmoticonType emoticonType, UserInfo userInfo) throws Exception{
        return new ResponseEntity<>(emoticonService.findByLike(emoticonType, userInfo), HttpStatus.OK);
    }

    @GetMapping("/dislikes")
    public ResponseEntity<?> findByDislike(@PathVariable("emoticonType") EmoticonType emoticonType, UserInfo userInfo) throws Exception{
        return new ResponseEntity<>(emoticonService.findByDislike(emoticonType, userInfo), HttpStatus.OK);
    }
}
