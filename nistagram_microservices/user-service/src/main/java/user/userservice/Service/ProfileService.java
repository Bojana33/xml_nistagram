package user.userservice.Service;

import user.userservice.Model.Profile;

import java.util.List;

public interface ProfileService {

    Profile create(Profile profile) throws Exception;
    Profile update(Profile profile) throws Exception;
    void delete(Long id);
    Profile findOne(Long id);
    List<Profile> findAll();
}
