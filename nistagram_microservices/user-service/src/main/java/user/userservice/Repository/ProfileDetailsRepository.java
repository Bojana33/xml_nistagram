package user.userservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import user.userservice.Model.ProfileDetails;

@Repository
public interface ProfileDetailsRepository extends JpaRepository<ProfileDetails,Long> {
}
