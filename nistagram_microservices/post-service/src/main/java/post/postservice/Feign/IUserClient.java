package post.postservice.Feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import post.postservice.Model2.User;

@FeignClient(value="user-service", url = "http://localhost:8089/user")
public interface IUserClient {
    @GetMapping("/{username}")
    ResponseEntity<User> getUser(@PathVariable String username);

    @GetMapping("/{username}/followers/{it}")
    String getFollower(@PathVariable("username") String username, @PathVariable("it") int ite);

    @GetMapping("/{username}/numOfFollowers")
    Integer getFollowersNum(@PathVariable("username") String username);
}
