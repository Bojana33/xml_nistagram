package user.userservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import user.userservice.Model.User;
import user.userservice.Model.UserRole;
import user.userservice.Service.UserService;

@RestController
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
        return "registracija.html";
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
        return "home.html";
    }


}
