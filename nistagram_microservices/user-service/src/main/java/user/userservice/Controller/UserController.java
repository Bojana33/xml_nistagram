package user.userservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import user.userservice.Model.User;
import user.userservice.Model.UserRole;
import user.userservice.Service.UserService;

import java.util.List;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String homePage(){
        return "home";
    }

    @GetMapping(value = "/{user}")
    public String userHomePage(@PathVariable(name = "user") String user, Model model){
        model.addAttribute("regUser", this.userService.findByUsername(user));
        return "regUserHome";
    }

    @GetMapping(value = "/registration")
    public String registration(Model model){
        User user = new User();
        model.addAttribute("user", user);
        user.setRole(UserRole.USER);
        //user.setActive(Boolean.TRUE);
        return "registration";
    }

    @PostMapping(value = "/saveUser")
    public String createUser(@ModelAttribute User user, BindingResult errors, Model model) throws Exception{
        model.addAttribute("userToSave", user);
        user.setRole(UserRole.USER);
        //user.setActive(Boolean.TRUE);
        this.userService.create(user);
        return "redirect:/" + user.getUsername();
    }

    @GetMapping(value = "/users")
    public String showUsers(Model model) {
        List<User> users = this.userService.findAll();
        model.addAttribute("usrs", users);
        model.addAttribute("usr", new User());
        return "users";
    }

    @GetMapping
    public String testiraj(Model model) {
        Integer id = 111;
        User user = this.userService.findOne(new Long(id));
        model.addAttribute("message",user.getEmail());
        return "test";
    }

}
