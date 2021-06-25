package post.postservice.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import post.postservice.Model.Post;
import post.postservice.Service.PostService;

import java.util.List;

@RestController
@RequestMapping(value = "/post")
public class PostController {

    @Autowired
    public PostService postService;

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        postService.delete(id);
    }

    @GetMapping("/posts/{username}")
    public ResponseEntity<?> findUserPosts(@PathVariable("username") String username) {
        List<Post> posts = postService.postsByUsername(username);
        return ResponseEntity.ok(posts);
    }

}

