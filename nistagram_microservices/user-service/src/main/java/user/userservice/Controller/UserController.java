package user.userservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import user.userservice.Model.User;
import user.userservice.Model.UserRole;
import user.userservice.Service.UserService;

import java.util.List;

@Controller
@RequestMapping(value = "/")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping(value = "/registration")
    public String registration(Model model){
        User user = new User();
        model.addAttribute("user", user);
        user.setRole(UserRole.USER);
        user.setActive(Boolean.TRUE);
        return "registration";
    }

    @PostMapping(value = "/saveUser")
    public String createUser(@ModelAttribute User user, BindingResult errors, Model model) throws Exception{
        if (this.userService.findByUsername(user.getUsername())!= null){
            throw new Exception("User with this username already exist.");
        }
        User userToSave = userService.create(user);
        model.addAttribute("userToSave", userToSave);
        userToSave.setRole(UserRole.USER);
        userToSave.setActive(Boolean.TRUE);
        return "home";
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
