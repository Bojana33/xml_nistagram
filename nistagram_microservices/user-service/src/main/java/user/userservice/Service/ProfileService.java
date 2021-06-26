package user.userservice.Service;

import user.userservice.Model.Profile;

import java.util.List;
import java.util.Optional;

public interface ProfileService {

    Profile create(Profile profile) throws Exception;
    Profile update(Profile profile) throws Exception;
    void delete(Long id);
    Profile findOne(Long id);
    List<Profile> findAll();
    Optional<Profile> findByDisplayName(String displayname);
}
