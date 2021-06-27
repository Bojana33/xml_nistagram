package post.postservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import post.postservice.Service.CommentService;

public class CommentController {

    @Autowired
    public CommentService commentService;
}
