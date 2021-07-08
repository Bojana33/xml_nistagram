package user.userservice.Model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "verification_requests")
public class VerificationRequest implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private CategoryType category;

    private String officialDocument;

    @Column
    private Boolean accepted;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "verification_sender_id", referencedColumnName = "id")
    private User verification_sender;

}
