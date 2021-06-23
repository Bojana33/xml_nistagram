package user.userservice.Model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("VerifiedProfile")
public class VerifiedProfile extends Profile {

    @Column
    private CategoryType category;

    @Column
    private String officialDocument;
}
