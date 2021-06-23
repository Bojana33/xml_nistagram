package post.postservice.Model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class UserInfo implements Serializable {

    @Id
    private Long userId;

    @Column
    private String username;

    @Column
    private String profilePicture;
}
