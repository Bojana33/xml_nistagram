package post.postservice.Model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Description implements Serializable {

    @Id
    private Long id;

    @Column
    private String content;
}
