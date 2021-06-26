package graph.graphservice.Service;

import graph.graphservice.Model.NodeDegree;
import graph.graphservice.Model.Users;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    public Users follow(Users follower, Users following);

    public Users addUser(Users user);

    public NodeDegree findNodeDegree(String username);

    public boolean isFollowing(String userA, String userb);

    public List<Users> findFollowers(String username);

    public List<Users> findFollowing(String username);



}
