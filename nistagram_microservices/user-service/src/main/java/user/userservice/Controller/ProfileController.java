package user.userservice.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import user.userservice.Exception.ResourceNotFoundException;
import user.userservice.Service.ProfileService;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping(value = "/users/{displayname}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findProfile(@PathVariable("displayname") String displayname) {
        log.info("retrieving user {}", displayname);

        return  profileService
                .findByDisplayName(displayname)
                .map(user -> ResponseEntity.ok(user))
                .orElseThrow(() -> new ResourceNotFoundException(displayname));
    }
}
