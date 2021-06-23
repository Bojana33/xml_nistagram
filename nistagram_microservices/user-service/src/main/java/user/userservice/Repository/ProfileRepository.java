package user.userservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import user.userservice.Model.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile,Long> {

}
