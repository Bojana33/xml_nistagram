package graph.graphservice.Model;

import lombok.Builder;

import javax.persistence.*;

@Builder
public class Friendship {

    @Id
    @GeneratedValue
    private Long id;

    //@StartNode
    private User startNode;

    //@EndNode
    private User endNode;
}
