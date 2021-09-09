package post.postservice.Model2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

        private Long id;

        private String username;

        private String name;

        private String lastname;

        private String password;

        private String email;

        private Boolean active = false;

        private String displayName;

        private String profilePicture;

        private Boolean verified;

        private Boolean privateProfile;

        private Set<String> followers;

}

