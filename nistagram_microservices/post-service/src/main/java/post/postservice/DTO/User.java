package post.postservice.DTO;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String username;

    @Column
    private String firstname;

    @Column
    private String lastname;

    @Column
    private String phone;
//
//    @Column
//    private String displayName;

//    @Column
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    private Date birthday;

    @Column
    private String biography;

    @Column
    private String website;

//    @Column
//    private Boolean verified = Boolean.FALSE;
//
//    @Column
//    private Boolean privateProfile;
//
//    @Column
//    private Boolean deactivated =Boolean.FALSE;
//
    @ElementCollection
    private Set<String> followers;
}
