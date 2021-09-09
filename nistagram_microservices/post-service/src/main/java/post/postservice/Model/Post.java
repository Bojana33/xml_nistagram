package post.postservice.Model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
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
    private String name;

    @Column
    private LocalDateTime createdAt;

    @Column
    private Date updatedAt;

    @Column
    private String caption;

    @Column
    private String tag;

    @Column
    private String location;

    @Column
    private String username;

    @Column
    private String imageUrl1;

    @Column
    private String imageUrl2;
    @Column
    private String imageUrl3;

    @Column
    private Long imageId;

    public Post( String caption, String username, String imageUrl1, String imageUrl2, String imageUrl3) {
        this.updatedAt = new Date();
        this.caption = caption;
        this.username = username;
        this.imageUrl1 = imageUrl1;
        this.imageUrl2 = imageUrl2;
        this.imageUrl3 = imageUrl3;
    }

    public Post(String caption, String imageUrl1, LocalDateTime createdAt){
        this.caption = caption;
        this.createdAt = createdAt;
        this.imageUrl1 = imageUrl1;
    }

    @ElementCollection
    private Set<String> likes = new HashSet<>();

    @ElementCollection
    private Set<String> dislikes = new HashSet<>();
}



