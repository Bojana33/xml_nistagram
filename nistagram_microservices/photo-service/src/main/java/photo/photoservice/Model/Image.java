package photo.photoservice.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Image implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String username;

    @Column
    private Date createdAt;

    @Column
    private String fileName;

    @Column
    private String uri;

}
