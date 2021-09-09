package post.postservice.Model2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Tag implements Serializable {

    private Long id;

    private String username;

    private String uri;

    private Set<Image> tagged = new HashSet<>();
}
