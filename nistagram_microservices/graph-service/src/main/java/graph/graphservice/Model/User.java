package graph.graphservice.Model;

import jdk.jfr.Relational;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.Relationship;

import javax.persistence.*;

import java.util.Set;

@Data
@Entity
@Builder
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String userId;
    private String username;
    private String name;
    private String profilePic;

    @Relationship(type = "IS_FOLLOWING")
    private Set<Friendship> friendships;

    public String getUsername() {
        return username;
    }

    void setUsername(String username){
        this.username = username;
    }

    public String getName(){
        return name;
    }

    void setName(String name){
        this.name = name;
    }

    public String getProfilePic(){
        return profilePic;
    }

    void setProfilePic(String profilePic){
        this.profilePic = profilePic;
    }

    public String getUserId(){
        return userId;
    }
}
