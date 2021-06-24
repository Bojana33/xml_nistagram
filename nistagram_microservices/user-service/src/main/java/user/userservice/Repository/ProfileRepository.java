package user.userservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import user.userservice.Model.Profile;

import java.util.List;

@Repository
public interface ProfileRepository extends JpaRepository<Profile,Long> {

    @Override
    List<Profile> findAll();

    @Override
    Profile getById(Long aLong);
}
