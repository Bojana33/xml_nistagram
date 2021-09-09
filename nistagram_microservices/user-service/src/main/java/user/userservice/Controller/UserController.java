package user.userservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import user.userservice.Feign.IFeignClient;
import user.userservice.Model.Request;
import user.userservice.Model.User;
import user.userservice.Service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
public class UserController {

    private UserService userService;
    private IFeignClient feignClient;

    @Autowired
    public UserController(UserService userService, IFeignClient feignClient) {
        this.userService = userService;
        this.feignClient = feignClient;
    }

    @GetMapping(path = "/feign")
    public ResponseEntity<String> test() {
        return feignClient.testMessage();
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

    @GetMapping("/{username}/followers/{it}")
    String getFollower(@PathVariable("username") String username, @PathVariable("it") int ite) {
        User u = this.userService.findByUsername(username);
        List<String> followers = new ArrayList<String>(u.getFollowers());

        return followers.get(ite);
    }

    @GetMapping("/{username}/numOfFollowers")
    Integer getFollowersNum(@PathVariable("username") String username) {
        User u = this.userService.findByUsername(username);
        Integer size = u.getFollowers().size();
        return size;
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
        return new ResponseEntity<>(this.userService.findByUsername(username).getFollowers(), HttpStatus.OK);
    }

    @GetMapping(value = "{username}/profile/getRequests")
    public ResponseEntity<Set<Request>> getRequests(@PathVariable("username") String sender) {
        return new ResponseEntity<>(this.userService.findByUsername(sender).getReceivedRequests(), HttpStatus.OK);
    }

    @GetMapping(value = "{sender}/profile/{receiver}/follow")
    public ResponseEntity<String> followUser(@PathVariable("sender") String sender, @PathVariable("receiver") String receiver){
        this.userService.followUser(sender, receiver);
        return new ResponseEntity<>("Request is sent", HttpStatus.OK);
    }

    @PostMapping(value = "{receiver}/profile/{sender}/handleRequests/{reqId}/{status}")
    public ResponseEntity<String> handleRequests(@PathVariable String receiver, @PathVariable String sender,@PathVariable Long reqId,@PathVariable Boolean status) {
        this.userService.handleRequests(receiver, sender, reqId, status);
        return new ResponseEntity<>("Request successfully resolved.", HttpStatus.OK);
    }
}

