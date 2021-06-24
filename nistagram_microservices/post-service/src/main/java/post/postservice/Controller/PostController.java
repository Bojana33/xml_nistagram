package post.postservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import post.postservice.Service.PostService;

public class PostController {

    @Autowired
    public PostService postService;
}

