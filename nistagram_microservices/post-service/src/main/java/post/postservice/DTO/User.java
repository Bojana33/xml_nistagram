package post.postservice.DTO;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import post.postservice.Model.Post;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String username;

    private String firstname;

    private String lastname;

    private String phone;
//
//    @Column
//    private String displayName;

//    @Column
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    private Date birthday;

    private String biography;

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
    private Set<String> followers;

}
