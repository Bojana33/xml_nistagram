package post.postservice.Model2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Image implements Serializable {

    private Long id;

    private String username;

    private Date createdAt;

    private String fileName;

    private String uri;

    private Set<Tag> tags = new HashSet<>();

}
