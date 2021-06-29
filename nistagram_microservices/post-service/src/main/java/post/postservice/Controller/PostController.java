package post.postservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import post.postservice.Model.EmoticonType;
import post.postservice.Model.Post;
import post.postservice.Service.PostService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/post")
public class PostController {


    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping(value = "/upload")
    public String createPost(@ModelAttribute Post post,
                             @RequestParam("file") MultipartFile file,
                             @RequestParam("caption") String caption,
                             @RequestParam("username") String username)  {
        postService.savePost(post, file, caption, username);
        return "post.html";
    }

    @GetMapping("/save")
    public String create(@ModelAttribute Post post,
                         BindingResult errors, Model model) throws Exception {
        Post postToSave = postService.create(post);
        model.addAttribute("postToSave", postToSave);
        postToSave.setUpdatedAt(new Date());
        postToSave.setCreatedAt(new Date());
        return "addpost.html";
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id, @RequestHeader(value = "username") String username) throws Exception {
        postService.delete(id);
    }

    @GetMapping("/posts/{username}")
    public ResponseEntity<?> findUserPosts(@PathVariable("username") String username) {
        List<Post> posts = postService.postsByUsername(username);
        return ResponseEntity.ok(posts);
    }

    @GetMapping(value = "/likes")
    public ResponseEntity<?> findByLike(@PathVariable("emoticonType") EmoticonType emoticonType, String username) throws Exception{
        List<Post> likes = postService.findByLike(emoticonType, username);
        return ResponseEntity.ok(likes);
    }

    @GetMapping(value = "/dislikes")
    public ResponseEntity<?> findByDislike(@PathVariable("emoticonType") EmoticonType emoticonType, String username) throws Exception{
        List<Post> dislikes = postService.findByDislike(emoticonType, username);
        return ResponseEntity.ok(dislikes);
    }
}

