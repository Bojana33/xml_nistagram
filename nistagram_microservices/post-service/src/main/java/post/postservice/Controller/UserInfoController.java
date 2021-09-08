package post.postservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import post.postservice.DTO.User;
import post.postservice.Service.impl.UserInfoServiceImpl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping(value = "/user")
public class UserInfoController {

    public UserInfoServiceImpl userInfoService;

    @Autowired
    private UserInfoController (UserInfoServiceImpl userInfoService) {
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
        if (user == null) { throw new Exception("User does not exist."); }
        model.addAttribute("user", user);
        return new ModelAndView("profile");
    }




}
