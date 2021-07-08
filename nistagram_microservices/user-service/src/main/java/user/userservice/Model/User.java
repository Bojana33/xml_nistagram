package user.userservice.Model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
@Table(name = "users")
public class User implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        @Column
        private String username;

        @Column
        private String name;

        @Column
        private String lastname;

        @Column
        private String password;

        @Column
        private String email;

        @Column
        private Boolean active;

        @Column
        private UserRole role;

        @Column
        private String phone;

        @Column
        private Gender gender;

        @Column
        private String displayName;

        @Column
        private String profilePicture;

        @Column
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private Date birthday;

        @Column
        private String biography;

        @Column
        private String website;

        @Column
        private Boolean verified = Boolean.FALSE;

        @Column
        private Boolean privateProfile;

        @Column
        private Boolean deactivated =Boolean.FALSE;

        @Column
        private Boolean tagMe = Boolean.TRUE;

        @Column
        private Boolean messagesFromUnfollowers = Boolean.TRUE;

        @OneToMany(mappedBy = "sender", targetEntity = Request.class)
        private Set<Request> sentRequests = new HashSet<>();

        @OneToMany(mappedBy = "receiver", targetEntity = Request.class)
        private Set<Request> receivedRequests = new HashSet<>();

        @ElementCollection
        private Set<String> blockedProfiles;

        @ElementCollection
        private Set<String> followers;

        @Column
        private Boolean followNotification = Boolean.TRUE;

        @Column
        private Boolean messageNotification = Boolean.TRUE;

        @Column
        private Boolean postNotification = Boolean.TRUE;

        @Column
        private Boolean commentNotification = Boolean.TRUE;


}

