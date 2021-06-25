package graph.graphservice.Controller;

import graph.graphservice.Model.User;
import graph.graphservice.Playload.FollowRequest;
import graph.graphservice.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users/followers")
    public ResponseEntity<?> follow(@RequestBody FollowRequest request) {

        log.info("received a follow request follow {} following {}",
                request.getFollower().getUsername(),
                request.getFollowing().getUsername());


        userService.follow(
                User.builder()
                        .userId(request.getFollower().getId())
                        .username(request.getFollower().getUsername())
                        .name(request.getFollower().getName())
                        .profilePic(request.getFollower().getProfilePicture())
                        .build(),

                User.builder()
                        .userId(request.getFollowing().getId())
                        .username(request.getFollowing().getUsername())
                        .name(request.getFollowing().getName())
                        .profilePic(request.getFollowing().getProfilePicture())
                        .build()
        );

        String message = String.format("user %s is following user %s",
                request.getFollower().getUsername(),
                request.getFollowing().getUsername());

        log.info(message);

        return ResponseEntity.ok(new ApiResponse(true, message));
    }

    @GetMapping("/users/{username}/degree")
    public ResponseEntity<?> findNodeDegree(@PathVariable String username) {
        log.info("received request to get node degree for {}", username);

        return ResponseEntity.ok(userService.findNodeDegree(username));
    }

    @GetMapping("/users/{usernameA}/following/{usernameB}")
    public ResponseEntity<?> isFollwoing(@PathVariable String usernameA, @PathVariable String usernameB) {
        log.info("received request to check is user {} is following {}"
                , usernameA, usernameB);

        return ResponseEntity.ok(userService.isFollowing(usernameA, usernameB));
    }

    @GetMapping("/users/{username}/followers")
    public ResponseEntity<?> findFollowers(@PathVariable String username) {
        return ResponseEntity.ok(userService.findFollowers(username));
    }
}

