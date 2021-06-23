package post.postservice.Model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Feed implements Serializable {

    @Id
    private Long id;
}
