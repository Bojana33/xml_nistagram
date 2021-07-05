package graph.graphservice.Playload;

import lombok.Data;

@Data
public class FollowRequest {

    UserPlayload follower;
    UserPlayload following;
}
