package post.postservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import post.postservice.DTO.Image;
import post.postservice.Model.EmoticonType;
import post.postservice.Model.Post;
import post.postservice.Service.PostService;

import java.util.Date;
import java.util.List;

@RestController
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping(value = "/upload")
    public ModelAndView createPost(@ModelAttribute Post post,
                             @RequestParam("file") MultipartFile file,
                             @RequestParam("caption") String caption,
                             @RequestParam("username") String username,
                             @RequestParam("image") Image image) {
        postService.savePost(post, file, caption, username, image);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("post");
        return modelAndView;
    }

    @GetMapping("/save")
    public ModelAndView create(@ModelAttribute Post post,
                         BindingResult errors, Model model) throws Exception {
        Post postToSave = postService.create(post);
        model.addAttribute("postToSave", postToSave);
        postToSave.setUpdatedAt(new Date());
        postToSave.setCreatedAt(new Date());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addpost");
        return modelAndView;
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id, @RequestHeader(value = "username") String username) throws Exception {
        postService.delete(id);
    }

    @GetMapping("/posts/{username}")
    public ResponseEntity<List<Post>> findUserPosts(@PathVariable("username") String username) {
        List<Post> posts = postService.postsByUsername(username);
        return new ResponseEntity(posts, HttpStatus.OK);
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

    //lajkovani ili dislajkovani
    @GetMapping(value = "/{username}/likes/{emoticonType}")
    public ResponseEntity<?> findByLike(@PathVariable EmoticonType emoticonType, @PathVariable String username) throws Exception{
        List<Post> likes = postService.findByEmoticons(emoticonType, username);
        return ResponseEntity.ok(likes);
    }

    @PostMapping(value = "/{username}/like/{id}")
    public ResponseEntity<String> likePost(@PathVariable String username, @PathVariable Long id){
        this.postService.likePost(username,id);
        return new ResponseEntity<>("Post is liked.", HttpStatus.OK);
    }

    @PostMapping(value = "/{username}/dislike/{id}")
    public ResponseEntity<String> dislikePost(@PathVariable String username,@PathVariable Long id){
        this.postService.dislikePost(username,id);
        return new ResponseEntity<>("Post is disliked.", HttpStatus.OK);
    }
}

