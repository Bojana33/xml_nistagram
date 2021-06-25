package post.postservice.Model;

import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@AllArgsConstructor
public class UserInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @Column
    private String username;

    @Column
    private String profilePicture;

    public UserInfo() {

    }
}
