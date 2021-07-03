package post.postservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Image {

    @Column
    private String username;

    @Column
    private Date createdAt;

    @Column
    private String fileName;

    @Column
    private String uri;

    Set<Image> taggedImages;

    public Album album;
}
