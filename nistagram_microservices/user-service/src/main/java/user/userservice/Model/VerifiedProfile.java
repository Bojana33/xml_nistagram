package user.userservice.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
@DiscriminatorValue("Verified Profile")
public class VerifiedProfile extends Profile {

    @Column
    private CategoryType category;

    @Column
    private String officialDocument;
}
