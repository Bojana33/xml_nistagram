package post.postservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import post.postservice.DTO.User;
import post.postservice.Model.Post;
import post.postservice.Service.impl.PostServiceImpl;
import post.postservice.Service.impl.UserInfoServiceImpl;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserInfoController {

    public UserInfoServiceImpl userInfoService;
    public PostServiceImpl postService;

    @Autowired
    private UserInfoController(UserInfoServiceImpl userInfoService) {
        this.userInfoService = userInfoService;
    }

//    @RequestMapping(value = "/profile")
//    public ModelAndView profile(Model model, User user) {
//        String username = user.getFirstname();
//        model.addAttribute("user", userInfoService.findByUsername(username));
//        return new ModelAndView("profile");
//    }

    @GetMapping("/profile/{id}")
    public ModelAndView showProfile(@PathVariable Long id, Model model) throws Exception {
        User user = this.userInfoService.findById(id);
        if (user == null) {
            throw new Exception("User does not exist.");
        }
        model.addAttribute("user", user);
        return new ModelAndView("profile");
    }

    @GetMapping("/feed/{username}")
    public ModelAndView showFeed(@PathVariable String username, Model model) throws Exception {
        User user = this.userInfoService.findByUsername(username);
        if (user == null) {
            throw new Exception("User does not exist.");
        }

        List<Post> posts = new ArrayList<>();
        for (String uname :
                user.getFollowers()) {
            posts.addAll(postService.postsByUsername(uname));
        }

        model.addAttribute("posts", posts);
        return new ModelAndView("feed");
    }
}
