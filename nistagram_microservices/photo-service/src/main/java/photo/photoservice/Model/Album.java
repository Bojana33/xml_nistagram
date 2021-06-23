package photo.photoservice.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Album implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String username;

    @Column
    private String uri;

    @Column
    private Date createdAt;
}
