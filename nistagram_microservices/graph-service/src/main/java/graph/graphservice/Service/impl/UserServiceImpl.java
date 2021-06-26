package graph.graphservice.Service.impl;

import graph.graphservice.Exception.UsernameAlreadyExistsException;
import graph.graphservice.Model.Friendship;
import graph.graphservice.Model.User;
import graph.graphservice.Repository.UserRepository;
import graph.graphservice.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;

@Service
@Slf4j
public class UserServiceImpl implements UserService {


    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User addUser(User user) {

        if(userRepository.findByUsername(user.getUsername()).isPresent()) {
            String message = String.format("username %s already exists", user.getUsername());
            log.warn(message);

            throw new UsernameAlreadyExistsException(message);
        }

        User saveUser = userRepository.save(user);

        log.info("user {} save successfully", saveUser.getUsername());

        return saveUser;
    }


    @Transactional
    public User follow(User follower, User following) {
        log.info("user {} will follow {}",
              follower.getUsername(), following.getUsername());

        User savedFollower = userRepository
                .findByUserId(follower.getUserId())
                .orElseGet(() -> {
                    //log.info("user {} not exists, creating it", follower.getUsername());
                    return this.addUser(follower);
                });

        User savedFollowing = userRepository
                .findByUserId(following.getUserId())
                .orElseGet(() -> {
                    //log.info("user {} not exits, creating it", following.getUsername());
                    return this.addUser(following);
                });

        if (savedFollower.getFriendships() == null) {
            savedFollower.setFriendships(new HashSet<>());
        }

        savedFollower
                .getFriendships()
                .add(Friendship.builder()
                        .startNode(savedFollower)
                        .endNode(savedFollowing)
                        .build());

        return userRepository.save(savedFollower);
    }
//
//    public NodeDegree findNodeDegree(String username) {
//        log.info("fetching degree for user {}", username);
//
//        long out = userRepository.findOutDegree(username);
//        long in = userRepository.findInDegree(username);
//
//        log.info("found {} outdegree and {} indegree for user {}", out, in, username);
//
//        return NodeDegree
//                .builder()
//                .outDegree(out)
//                .inDegree(in)
//                .build();
//    }
//
//    public boolean isFollowing(String userA, String userb) {
//        return userRepository.isFollowing(userA, userb);
//    }
//
//    public List<User> findFollowers(String username) {
//        List<User> followers = userRepository.findFollowers(username);
//        log.info("found {} followers for user {}", followers.size(), username);
//
//        return followers;
//    }
//
//    public List<User> findFollowing(String username) {
//        List<User> following = userRepository.findFollowing(username);
//        log.info("found {} that user {} is following", following.size(), username);
//
//        return following;
//    }
}
