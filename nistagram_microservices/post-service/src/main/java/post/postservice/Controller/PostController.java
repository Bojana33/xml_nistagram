package post.postservice.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import post.postservice.Model.Post;
import post.postservice.Service.PostService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/post")
public class PostController {

    @Autowired
    public PostService postService;
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
    @PostMapping("")
    public void createPost(MultipartFile file, String caption, String username) throws Exception {
        Post p = new Post();

        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        if(filename.contains(".."))
        {
            System.out.println("Not a valid file");
        }
        try {
            p.setImageUrl(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        p.setCaption(caption);
        p.setUpdatedAt(new Date());
        p.setCreatedAt(new Date());
        p.setUsername(username);
        postService.create(p);

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

}

