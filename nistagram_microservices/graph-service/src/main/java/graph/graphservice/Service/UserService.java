package graph.graphservice.Service;

import graph.graphservice.Model.User;

public interface UserService {

    public User follow(User follower, User following);

    public User addUser(User user);

//    public NodeDegree findNodeDegree(String username);
//
//    public boolean isFollowing(String userA, String userb);
//
//    public List<User> findFollowers(String username);
//
//    public List<User> findFollowing(String username);



}
