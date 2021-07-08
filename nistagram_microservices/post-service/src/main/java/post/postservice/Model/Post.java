package post.postservice.Model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
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

    @ElementCollection
    private Collection<String> imageUrl;

    @ElementCollection
    private Set<String> likes = new HashSet<>();

    @ElementCollection
    private Set<String> dislikes = new HashSet<>();
}



