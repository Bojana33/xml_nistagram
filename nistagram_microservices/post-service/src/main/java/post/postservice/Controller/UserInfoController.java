package post.postservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import post.postservice.Feign.IUserClient;
import post.postservice.Model2.User;
//import post.postservice.Service.UserInfoService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserInfoController {

    /*@Autowired
    public UserInfoService userInfoService;*/
    @Autowired
    public IUserClient userClient;


    @GetMapping(value = "/tst/{username}")
    public ResponseEntity<User> getUser(@PathVariable String username) {
        return userClient.getUser(username);
    }

    @GetMapping("/{username}/followers")
    private List<String> getFollowers(@PathVariable("username") String username) {
        List<String> followers = new ArrayList<>();

        for (int i = 0; i < userClient.getFollowersNum(username); i++) {
            followers.add(userClient.getFollower(username, i));
        }

        return followers;
    }

    @GetMapping("/current")
    private String getCurrent() {
        return this.userClient.getCurrent();
    }
}
