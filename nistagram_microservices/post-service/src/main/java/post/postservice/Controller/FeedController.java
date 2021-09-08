package post.postservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import post.postservice.Model.Post;
import post.postservice.Service.FeedService;
import post.postservice.Service.impl.FeedServiceImpl;
import post.postservice.Service.impl.PostServiceImpl;

import java.util.List;

@RestController
@RequestMapping(value = "/feed")
public class FeedController {

    public FeedServiceImpl feedService;

    public PostServiceImpl postService;

    @Autowired
    FeedController (FeedServiceImpl feedService, PostServiceImpl postService) {
        this.feedService = feedService;
        this.postService = postService;
    }

    @RequestMapping("/")
    public ModelAndView feed(@PathVariable String username) {
        return new ModelAndView("feed");
    }

    @GetMapping("/{username}")
    public ModelAndView findUserFeed(@PathVariable("username") String username) {
        List<Post> posts = postService.postsByUsername(username);
        return new ModelAndView("feed");
    }
}
