package user.userservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.support.Repositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import user.userservice.Model.Request;
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


    @GetMapping(value = "{username}/profile/getFollowers")
    public ResponseEntity<Set<String>> getFollowers(@PathVariable String username) {
        return new ResponseEntity<>(this.userService.findByUsername(username).getSenderFollowers(), HttpStatus.OK);
    }

    @GetMapping(value = "{username}/profile/getRequests")
    public ResponseEntity<Set<String>> getRequests(@PathVariable String sender) {
        return new ResponseEntity<>(this.userService.findByUsername(sender).getRequests(), HttpStatus.OK);
    }

    @PostMapping(value = "{sender}/profile/{receiver}/follow")
    public ResponseEntity<String> follow(@PathVariable String sender, @PathVariable User receiver){
        this.userService.follow(sender, receiver);
        return new ResponseEntity<>("mmlccjfj", HttpStatus.OK);
    }

    @PostMapping(value = "{receiver}/profile/handleRequests")
    public ResponseEntity<String> handleRequests(@PathVariable String receiver, String sender, Request request) {
        this.userService.handleRequest(receiver, sender, request);
        return new ResponseEntity<>("Request successfully resolved.", HttpStatus.OK);
    }
}

