package photo.photoservice.Model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Tag implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String username;

    @Column
    private String uri;
}
