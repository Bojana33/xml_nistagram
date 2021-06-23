package post.postservice.Model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Emoticon implements Serializable {

    @Id
    private Long id;

    @Column
    private EmoticonType emoticonType;
}
