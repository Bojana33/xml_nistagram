package post.postservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import post.postservice.Feign.IPhotoClient;
import post.postservice.Feign.IUserClient;
import post.postservice.Model.EmoticonType;
import post.postservice.Model.Post;
import post.postservice.Service.impl.PostServiceImpl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
public class PostController {

    private PostServiceImpl postService;
    private IPhotoClient photoClient;
    private IUserClient userClient;

    @Autowired
    public PostController(PostServiceImpl postService, IPhotoClient photoClient, IUserClient userClient) {
        this.postService = postService;
        this.photoClient = photoClient;
        this.userClient = userClient;
    }

    public static String uploadDirectory = System.getProperty("images","/uploads");

//    @PostMapping(value = "/upload")
//    public ResponseEntity<?> createPost(@RequestParam PostRequest postRequest) {
//        Post post = postService.savePost(postRequest);
//        return new ResponseEntity(post, HttpStatus.OK);
//    }

    @GetMapping("/save")
    public ModelAndView create(@ModelAttribute Post post,
                         BindingResult errors, Model model) throws Exception {
        Post postToSave = postService.create(post);
        model.addAttribute("postToSave", postToSave);
        postToSave.setUpdatedAt(new Date());
        //postToSave.setCreatedAt(new Date());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addpost");
        return modelAndView;
    }


//    @RequestMapping("/")
//    public String UploadImage(Model model){
//        return "addpost";
//    }

    @RequestMapping("/uploadd")
    public ModelAndView upload(Model model, @RequestParam("files") MultipartFile[] files) {
        StringBuilder fileNames = new StringBuilder();
        for (MultipartFile file : files) {
            Path fileNameAndPath = Paths.get(uploadDirectory, file.getName());
            fileNames.append(file.getName());
            try {
                Files.write(fileNameAndPath, file.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //model.addAttribute("msg", "Successfully uploaded files." + fileNames.toString());
        return new ModelAndView("post");
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id, @RequestHeader(value = "username") String username) throws Exception {
        postService.delete(id);
    }

    @GetMapping("/posts/{username}")
    public ResponseEntity<?> findUserPosts(@PathVariable("username") String username) {
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

    @RequestMapping("/")
    public ModelAndView upl(Model model) {
        Post post = new Post();
        model.addAttribute("post",post);
        return new ModelAndView("addpostt");
    }

    @PostMapping("/sav")
    public ModelAndView sav(@RequestParam("imageUrl") MultipartFile imageUrl,@RequestParam("cpt") String cpt, ModelMap model, @ModelAttribute Post post) {
        Path path = Paths.get("/media/boris/Faks/FTN8/XWS/xml_nistagram/nistagram_microservices/post-service/uploads/");
        try {
            InputStream inputStream = imageUrl.getInputStream();
            Files.copy(inputStream, path.resolve(imageUrl.getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING);
            post.setImageUrl1(imageUrl.getOriginalFilename().toLowerCase());
            post.setCreatedAt(LocalDateTime.now());
            post.setCaption(cpt);
            this.postService.savePost(post);
            model.addAttribute("post", post);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ModelAndView("viewpost");
    }

}

