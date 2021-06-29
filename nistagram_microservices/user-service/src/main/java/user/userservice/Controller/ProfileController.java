package user.userservice.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import user.userservice.Exception.ResourceNotFoundException;
import user.userservice.Model.Profile;
import user.userservice.Model.User;
import user.userservice.Service.ProfileService;
import org.springframework.web.bind.annotation.*;
import user.userservice.Service.UserService;


@Slf4j
@Controller
public class ProfileController {

    private ProfileService profileService;
    private UserService userService;

    @Autowired
    public  ProfileController(ProfileService profileService,UserService userService){
        this.profileService=profileService;
        this.userService=userService;
    }
    @GetMapping(value = "/{user}/profile")
    public String createProfile(@PathVariable(name = "user") String user, Model model){
        Profile profile = new Profile();
        profile.setUser(userService.findByUsername(user));
        model.addAttribute("profile", profile);
        return "createProfile";
    }

    @PostMapping(value = "/saveProfile")
    public String saveProfile(@ModelAttribute User user, @ModelAttribute Profile profile, BindingResult errors, Model model) throws Exception{
        model.addAttribute("profileToSave", profile);
        this.profileService.create(profile);
        return "redirect:/"+ user.getUsername()+ "/profile";
    }

    @GetMapping(value = "/users/{displayname}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findProfile(@PathVariable("displayname") String displayname) {
        log.info("retrieving user {}", displayname);

        return  profileService
                .findByDisplayName(displayname)
                .map(user -> ResponseEntity.ok(user))
                .orElseThrow(() -> new ResourceNotFoundException(displayname));
    }
}
