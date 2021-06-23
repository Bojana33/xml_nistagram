package user.userservice.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

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

    @OneToOne
    private ProfileDetails profileDetails;
}
