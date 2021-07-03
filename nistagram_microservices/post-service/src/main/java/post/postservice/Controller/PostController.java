package post.postservice.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import post.postservice.Model.Post;
import post.postservice.Service.PostService;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService){
        this.postService = postService;
    }

    /*@PostMapping()
    public ResponseEntity<?> createPost(@RequestBody , @RequestHeader(value= "username") String username) throws Exception {
        Post newPost = postService.create();

        return ResponseEntity.created();
    }*/
/*
    @PostMapping()
    public ResponseEntity<String> createModel(@RequestBody ModelDTO modelDTO, @RequestHeader("Username") String username){
        logger.info("Admin {} je zatrazio kreiranje modela {} za brend{}. {}", username, modelDTO.getModelName(), modelDTO.getBrandName(), LocalDateTime.now());
        return modelService.createModel(modelDTO.getBrandName(), modelDTO.getModelName(), username);
    }*/
//
//    @DeleteMapping("/delete/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void delete(@PathVariable("id") Long id, @RequestHeader(value = "username") String username) throws Exception {
//
//        postService.delete(id, username);
//    }
//
//    @GetMapping("/posts/{username}")
//    public ResponseEntity<?> findUserPosts(@PathVariable("username") String username) {
//        List<Post> posts = postService.postsByUsername(username);
//        return ResponseEntity.ok(posts);
//    }

}

