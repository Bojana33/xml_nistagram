package user.userservice.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.yaml.snakeyaml.tokens.FlowEntryToken;

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
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "VerifiedProfile", discriminatorType = DiscriminatorType.STRING)
public class Profile implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

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

    @OneToOne//(fetch = FetchType.LAZY)
    private User user;

    @ManyToMany
    @JoinTable(name="BlockedProfiles", joinColumns = @JoinColumn(name="ProfileId",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "BlockedProfileId", referencedColumnName = "id"))
    private Set<Profile> blockedProfiles = new HashSet<>();

}
