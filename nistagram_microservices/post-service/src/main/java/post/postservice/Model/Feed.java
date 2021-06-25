package post.postservice.Model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Feed implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
