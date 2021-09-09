package photo.photoservice.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Image implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String username;

    @Column
    private LocalDate createdAt;

    @Column
    private String fileName;

    @Column
    private String uri;

    @ManyToMany
    @JoinTable(
            name="tagged_images",
            joinColumns=@JoinColumn(name = "image_id"),
            inverseJoinColumns=@JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags = new HashSet<>();

    @ManyToOne
    @JoinColumn(name="album_fk")
    public Album album;
}
