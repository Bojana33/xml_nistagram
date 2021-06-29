package user.userservice.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
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

        @OneToOne//(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
        private Profile profile;

        @OneToMany(mappedBy = "sender", targetEntity = Request.class)
        private Set<Request> sentRequests = new HashSet<>();

        @OneToMany(mappedBy = "receiver", targetEntity = Request.class)
        private Set<Request> receivedRequests = new HashSet<>();

}

