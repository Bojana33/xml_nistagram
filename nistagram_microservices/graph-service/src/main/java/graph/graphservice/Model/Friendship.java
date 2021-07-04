package graph.graphservice.Model;

import lombok.Builder;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//@RelationshipEntity("IS_FOLLOWING")
@Builder
public class Friendship {

    @Id
    @GeneratedValue
    private Long id;

    //@StartNode
    @Column
    private User startNode;

    //@EndNode
    @Column
    private User endNode;
}
