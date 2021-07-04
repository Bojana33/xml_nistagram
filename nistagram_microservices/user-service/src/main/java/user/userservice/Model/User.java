package user.userservice.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
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

        @ManyToMany
        @JoinTable(name="BlockedProfiles", joinColumns = @JoinColumn(name="ProfileId",referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "BlockedProfileId", referencedColumnName = "id"))
        private Set<User> blockedProfiles = new HashSet<>();

}

