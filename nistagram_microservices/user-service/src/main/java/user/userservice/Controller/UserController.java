package user.userservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import user.userservice.Feign.IFeignClient;
import user.userservice.Model.CategoryType;
import user.userservice.Model.User;
import user.userservice.Model.UserRole;
import user.userservice.Model.VerificationRequest;
import user.userservice.Service.UserService;
import user.userservice.Service.VerificationRequestService;

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

    @GetMapping("/feign")
    public ResponseEntity<String> test() {
        //return new ResponseEntity(feignClient.testMessage(), HttpStatus.OK);
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
    public ResponseEntity<User> getUser(@PathVariable String username){
        return new ResponseEntity<>(this.userService.findByUsername(username), HttpStatus.OK);
    }

    @GetMapping(value = "/{username}/verificationRequests")
    public ResponseEntity<List<VerificationRequest>> showVerificationRequests(@PathVariable String username) throws Exception{
        User user = this.userService.findByUsername(username);
        if (user == null) {
            throw new Exception("User does not exist");
        }
        if (user.getRole()!= UserRole.ADMIN) {
            throw new Exception("Access denied");
        }
        return new ResponseEntity<>(this.verificationService.findAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/{username}/sendVerificationRequest")
    public ResponseEntity<String> sendVerificationRequest(@PathVariable String username, @RequestParam("category") CategoryType category,
                                                          @RequestParam("document") String document) throws Exception {
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
        vr.setOfficialDocument(document);
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
    public ResponseEntity<User> updateProfile(@RequestBody User user) throws Exception{
        if(this.userService.findByUsername(user.getUsername()) != null) {
            User userToUpdate = this.userService.update(user);
            return new ResponseEntity<>(userToUpdate, HttpStatus.OK);
        }
        throw new Exception("User with this username doesn't exist");
    }

    @PostMapping(value = "profile/{username}/privacySettings")
    public ResponseEntity<User> privacySettings(@PathVariable String username, @RequestParam(value = "tagMe") Boolean tagMe,
                                                @RequestParam(value = "messagesFromUnfollowers") Boolean messagesFromUnfollowers,
                                                @RequestParam(value = "privateProfile") Boolean privateProfile) throws Exception {
        if(this.userService.findByUsername(username) != null) {
            User userToUpdate = this.userService.privacySettings(username,tagMe,messagesFromUnfollowers,privateProfile);
            return new ResponseEntity<>(userToUpdate, HttpStatus.OK);
        }
        throw new Exception("User with this username doesn't exist");
    }

    @PostMapping(value = "{username}/profile/{username2}/block")
    public ResponseEntity<String> blockUser(@PathVariable String username, @PathVariable String username2){
        this.userService.blockUser(username,username2);
        return new ResponseEntity<>("User is blocked.", HttpStatus.OK);
    }

    @GetMapping(value = "{username}/profile/getBlockedUsers")
    public ResponseEntity<Set<String>> getBlockedUsers(@PathVariable String username){
        return new ResponseEntity<>(this.userService.findByUsername(username).getBlockedProfiles(),HttpStatus.OK);
    }

    @PostMapping(value = "profile/{username}/notificationSettings")
    public ResponseEntity<User> notificationSettings(@PathVariable String username, @RequestParam(value = "") Boolean messageNotification,
                                                @RequestParam(value = "") Boolean postNotification,
                                                @RequestParam(value = "") Boolean commentNotification,
                                                @RequestParam(value = "") Boolean followNotification) throws Exception {
        if(this.userService.findByUsername(username) != null) {
            User userToUpdate = this.userService.notificationSettings(username,messageNotification,postNotification,commentNotification,followNotification);
            return new ResponseEntity<>(userToUpdate, HttpStatus.OK);
        }
        throw new Exception("User with this username doesn't exist");
    }
}
