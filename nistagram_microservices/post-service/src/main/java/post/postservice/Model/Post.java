package post.postservice.Model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Date createdAt;

    @Column
    private Date updatedAt;

    @Column
    private String caption;

    @Column
    private String username;

    @Column
    private String imageUrl;

    @ManyToMany
    @JoinTable(name="likes", joinColumns = @JoinColumn(name="UserId",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "likesId", referencedColumnName = "id"))
    private Set<Post> likes = new HashSet<>();

    @ManyToMany
    @JoinTable(name="dislikes", joinColumns = @JoinColumn(name="UserId",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "dislikesId", referencedColumnName = "id"))
    private Set<Post> dislikes = new HashSet<>();
}



