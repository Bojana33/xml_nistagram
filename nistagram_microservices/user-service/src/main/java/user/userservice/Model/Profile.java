package user.userservice.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Profile implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String displayName;

    @Column
    private String profilePicture;

    @Column
    private Date birthday;

    @Column
    private String biography;

    @Column
    private String website;

    @Column
    private Boolean verified;

    @Column
    private Boolean privateProfile;

    @Column
    private Boolean deactivated;

    @ManyToMany
    @JoinTable(name="BlockedProfiles", joinColumns = @JoinColumn(name="ProfileId",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "BlockedProfileId", referencedColumnName = "id"))
    private Set<Profile> blockedProfiles = new HashSet<>();

    public Boolean getPrivateProfile() {
        return privateProfile;
    }
}
