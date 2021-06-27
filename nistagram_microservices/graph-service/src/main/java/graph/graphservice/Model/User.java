package graph.graphservice.Model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.Relationship;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Set;

@Data
@Getter
@Setter
//@NodeEntity
@Builder
@Table(name = "users")
public class User {

    @Id
    @org.springframework.data.neo4j.core.schema.Id
    @GeneratedValue
    private Long id;

    @Column
    private String userId;

    @Column
    private String username;

    @Column
    private String name;

    @Column
    private String profilePic;

    @Relationship(type = "IS_FOLLOWING")
    private Set<Friendship> friendships;
}
