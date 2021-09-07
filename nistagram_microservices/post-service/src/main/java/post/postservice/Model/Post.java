package post.postservice.Model;

import lombok.*;
import post.postservice.DTO.Album;

import javax.persistence.*;
import java.io.File;
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
    private String name;

    @Column
    private Date createdAt;

    @Column
    private Date updatedAt;

    @Column
    private String caption;

    @Column
    private String username;

    @Column
    private String imageUrl1;
    @Column
    private String imageUrl2;
    @Column
    private String imageUrl3;

    public Post( String caption, String username, String imageUrl1, String imageUrl2, String imageUrl3) {
        this.createdAt = new Date();
        this.updatedAt = new Date();
        this.caption = caption;
        this.username = username;
        this.imageUrl1 = imageUrl1;
        this.imageUrl2 = imageUrl2;
        this.imageUrl3 = imageUrl3;
    }

    @ElementCollection
    private Set<String> likes = new HashSet<>();

    @ElementCollection
    private Set<String> dislikes = new HashSet<>();
}



