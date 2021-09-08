package post.postservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import post.postservice.Service.FeedService;
import post.postservice.Service.impl.FeedServiceImpl;

@RestController
@RequestMapping(value = "/feed")
public class FeedController {

    public FeedServiceImpl feedService;

    @Autowired
    FeedController (FeedServiceImpl feedService) {this.feedService = feedService; }

    @RequestMapping("/")
    public ModelAndView feed() {
        return new ModelAndView("feed");
    }
}
