package post.postservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Album{

    @Column
    private String username;

    @Column
    private String uri;

    @Column
    private Date createdAt;

    @OneToMany(mappedBy="album")
    private Set<Image> images;

}

