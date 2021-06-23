package post.postservice.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Post implements Serializable {

    @Id
    private Long id;

    @Column
    private Date createdAt;

    @Column
    private Date updatedAt;

    @Column
    private String caption;
}
