package user.userservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import user.userservice.Model.User;
import user.userservice.Service.UserService;

import java.util.List;

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

    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> showUsers() {
        return new ResponseEntity<>(this.userService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{username}")
    public ResponseEntity<User> getUser(@PathVariable String username){
        return new ResponseEntity<>(this.userService.findByUsername(username), HttpStatus.OK);
    }

    @PostMapping(value = "/updateProfile")
    public ResponseEntity<User> updateProfile(@RequestBody User user) throws Exception{
        User userToUpdate = this.userService.findByUsername(user.getUsername());
        return new ResponseEntity<>(this.userService.update(userToUpdate), HttpStatus.OK);
    }

    @PostMapping(value = "profile/privacySettings")
    public ResponseEntity<User> privacySettings(@RequestBody User user) throws Exception {
        User userToUpdate = this.userService.findByUsername(user.getUsername());
        return new ResponseEntity<>(this.userService.privacySettings(userToUpdate), HttpStatus.OK);
    }
}
