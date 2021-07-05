package user.userservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import user.userservice.Model.User;
import user.userservice.Model.UserRole;
import user.userservice.Service.UserService;

import java.util.List;
import java.util.Set;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/saveUser")
    public ResponseEntity<String> createUser(@RequestBody User user) throws Exception {
        if (userService.findByUsername(user.getUsername()) != null) {
            return new ResponseEntity<String>("Username already exist", HttpStatus.BAD_REQUEST);
        }
        this.userService.create(user);
        return new ResponseEntity<String>("User is created", HttpStatus.OK);
    }
//
//    @PostMapping(value = "/saveUser")
//    public String createUser(@ModelAttribute User user, BindingResult errors, Model model) throws Exception{
//        if (this.userService.findByUsername(user.getUsername())!= null){
//            throw new Exception("User with this username already exist.");

    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> showUsers() {
        return new ResponseEntity<>(this.userService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{username}")
    public ResponseEntity<User> getUser(@PathVariable String username) {
        return new ResponseEntity<>(this.userService.findByUsername(username), HttpStatus.OK);
    }

    @PostMapping(value = "/updateProfile")
    public ResponseEntity<User> updateProfile(@RequestBody User user) throws Exception {
        if (this.userService.findByUsername(user.getUsername()) != null) {
            User userToUpdate = this.userService.update(user);
            return new ResponseEntity<>(userToUpdate, HttpStatus.OK);
        }
//        User userToSave = userService.create(user);
//        model.addAttribute("userToSave", userToSave);
//        userToSave.setRole(UserRole.USER);
//        userToSave.setActive(Boolean.TRUE);
//        return "home.html";
        throw new Exception("User with this username doesn't exist");
    }

    @PostMapping(value = "profile/{username}/privacySettings")
    public ResponseEntity<User> privacySettings(@PathVariable String username, @RequestParam(value = "tagMe") Boolean tagMe,
                                                @RequestParam(value = "messagesFromUnfollowers") Boolean messagesFromUnfollowers,
                                                @RequestParam(value = "privateProfile") Boolean privateProfile) throws Exception {
        if (this.userService.findByUsername(username) != null) {
            User userToUpdate = this.userService.privacySettings(username, tagMe, messagesFromUnfollowers, privateProfile);
            return new ResponseEntity<>(userToUpdate, HttpStatus.OK);
        }
        throw new Exception("User with this username doesn't exist");
    }

    @PostMapping(value = "{username}/profile/{username2}/block")
    public ResponseEntity<String> blockUser(@PathVariable String username, @PathVariable String username2) {
        this.userService.blockUser(username, username2);
        return new ResponseEntity<>("User is blocked.", HttpStatus.OK);
    }

    @GetMapping(value = "{username}/profile/getBlockedUsers")
    public ResponseEntity<Set<String>> getBlockedUsers(@PathVariable String username) {
        return new ResponseEntity<>(this.userService.findByUsername(username).getBlockedProfiles(), HttpStatus.OK);
    }

    @PostMapping(value = "profile/{username}/notificationSettings")
    public ResponseEntity<User> notificationSettings(@PathVariable String username, @RequestParam(value = "") Boolean messageNotification,
                                                     @RequestParam(value = "") Boolean postNotification,
                                                     @RequestParam(value = "") Boolean commentNotification,
                                                     @RequestParam(value = "") Boolean followNotification) throws Exception {
        if (this.userService.findByUsername(username) != null) {
            User userToUpdate = this.userService.notificationSettings(username, messageNotification, postNotification, commentNotification, followNotification);
            return new ResponseEntity<>(userToUpdate, HttpStatus.OK);
        }
        throw new Exception("User with this username doesn't exist");
    }

    @GetMapping(value = "/registration")
    public String registration(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        user.setRole(UserRole.USER);
        user.setActive(Boolean.TRUE);
        return "registracija.html";
    }
}
