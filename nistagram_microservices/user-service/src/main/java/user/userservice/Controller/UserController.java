package user.userservice.Controller;

import org.bouncycastle.math.raw.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import user.userservice.DTO.UserDTO;
import user.userservice.Model.Request;
import user.userservice.Model.CategoryType;
import user.userservice.Model.User;
import user.userservice.Model.UserRole;
import user.userservice.Model.VerificationRequest;
import user.userservice.Service.UserService;
import user.userservice.Service.VerificationRequestService;
import user.userservice.Feign.IFeignClient;
import java.util.ArrayList;

import java.util.List;
import java.util.Set;

@RestController
public class UserController {

    private UserService userService;
    private VerificationRequestService verificationService;
    private IFeignClient feignClient;

    @Autowired
    public UserController(UserService userService, VerificationRequestService verificationService, IFeignClient feignClient) {
        this.userService = userService;
        this.verificationService = verificationService;
        this.feignClient = feignClient;
    }

    @GetMapping(path = "/feign")
    public ResponseEntity<String> test() {
        return feignClient.testMessage();
    }

    @GetMapping("/index")
    @PreAuthorize("hasAnyRole('ADMIN','USER','AGENT')")
    public ModelAndView indexPage(Authentication auth, Model model) {
        User u = this.userService.findByUsername(auth.getName());
        model.addAttribute("user",u);
        return new ModelAndView("indexPage");
    }

    @GetMapping("/home")
    public ModelAndView home(){
        return new ModelAndView("home");
    }

    @GetMapping("/login")
    public ModelAndView loginForm(Model model) {
        UserDTO user = new UserDTO();
        model.addAttribute("user", user);
        return new ModelAndView("login");
    }

    @GetMapping("/registration")
    public ModelAndView registration(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return new ModelAndView("registration");
    }

    @PostMapping(value = "/saveUser")
    public ResponseEntity<String> createUser(@ModelAttribute User user) throws Exception {
        if (userService.findByUsername(user.getUsername()) != null) {
            return new ResponseEntity<String>("Username already exist", HttpStatus.BAD_REQUEST);
        }
        user.setUserRole(UserRole.USER);
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

    @GetMapping(value = "/{username}/verificationRequests")
    public ResponseEntity<List<VerificationRequest>> showVerificationRequests(@PathVariable String username) throws Exception{
        User user = this.userService.findByUsername(username);
        if (user == null) {
            throw new Exception("User does not exist");
        }
        if (user.getUserRole()!= UserRole.ADMIN) {
            throw new Exception("Access denied");
        }
        return new ResponseEntity<>(this.verificationService.findAll(), HttpStatus.OK);
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

    @PostMapping(value = "/{username}/sendVerificationRequest")
    public ResponseEntity<String> sendVerificationRequest(@PathVariable String username, @RequestParam("category") CategoryType category) throws Exception {
        User user = userService.findByUsername(username);
        if (user == null) {
            return new ResponseEntity<String>("User with this username does not exist",
                    HttpStatus.BAD_REQUEST);
        }
        if (user.getVerificationRequest() != null) {
            return new ResponseEntity<String>("Verification request already sent.",
                    HttpStatus.BAD_REQUEST);
        }
        VerificationRequest vr = new VerificationRequest();
        vr.setVerification_sender(user);
        //vr.setOfficialDocument(user.getOfficialDocument());
        vr.setCategory(category);
        this.verificationService.create(vr);
        user.setVerificationRequest(vr);
        this.userService.update(user);

        return new ResponseEntity<String>("Verification request sent", HttpStatus.OK);
    }

    @PostMapping(value = "/saveVerificationRequest")
    public ResponseEntity<String> createVerificationRequest(@RequestBody VerificationRequest verificationRequest) throws Exception {
        if (verificationService.findOne(verificationRequest.getId()) != null) {
            return new ResponseEntity<String>("Verification request with this id already exist", HttpStatus.BAD_REQUEST);
        }
        this.verificationService.create(verificationRequest);
        return new ResponseEntity<String>("Verification request is created", HttpStatus.OK);
    }

    @GetMapping(value = "/verificationRequests/{id}")
    private ResponseEntity<VerificationRequest> getVerificationRequest(@PathVariable Long id) {
        return new ResponseEntity<>(this.verificationService.findOne(id), HttpStatus.OK);
    }

    @PostMapping(value = "/{username}/verificationRequests/{id}")
    public ResponseEntity<String> approveVerificationRequest(
            @PathVariable String username, @PathVariable Long id) throws Exception{
        User user = userService.findByUsername(username);
        if (user == null) {
            return new ResponseEntity<String>("User with this username does not exist",
                    HttpStatus.BAD_REQUEST);
        }
        if (user.getVerificationRequest() != null) {
            return new ResponseEntity<String>("Verification request already sent.",
                    HttpStatus.BAD_REQUEST);
        }
        VerificationRequest vr = this.verificationService.findOne(id);
        vr.setAccepted(true);
        this.verificationService.update(vr);
        vr.getVerification_sender().setVerified(true);
        this.userService.update(vr.getVerification_sender());
        return new ResponseEntity<>("Verification request approved", HttpStatus.OK);
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

    @PostMapping(value = "{usernameUnfollowing}/profile/{usernameToUnfollow}/unfollow")
    public ResponseEntity<String> unfollowUser(@PathVariable String usernameUnfollowing, @PathVariable String usernameToUnfollow){
        this.userService.unfollowUser(usernameUnfollowing,usernameToUnfollow);
        return new ResponseEntity<>("User is unfollowed.", HttpStatus.OK);
    }

    @PostMapping(value = "{usernameUnblocking}/profile/{usernameToUnblock}/unblock")
    public ResponseEntity<String> unblockUser(@PathVariable String usernameUnblocking, @PathVariable String usernameToUnblock){
        this.userService.unblockUser(usernameUnblocking,usernameToUnblock);
        return new ResponseEntity<>("User is unblocked", HttpStatus.OK);
    }
}

