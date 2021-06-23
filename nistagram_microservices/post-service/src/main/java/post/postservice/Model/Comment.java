package post.postservice.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Comment implements Serializable {

    @Id
    private Long id;

    @Column
    private String content;

    @Column
    private Date commentDate;
}
