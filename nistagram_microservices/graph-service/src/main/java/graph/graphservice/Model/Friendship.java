package graph.graphservice.Model;

import lombok.Builder;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import org.neo4j.ogm.annotation.*;

@RelationshipEntity ("IS_FOLLOWING")
@Builder
public class Friendship implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    //@StartNode
    private Users startNode;

    //@EndNode
    private Users endNode;
}
