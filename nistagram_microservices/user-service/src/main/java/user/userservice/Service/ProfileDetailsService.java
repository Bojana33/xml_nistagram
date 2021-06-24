package user.userservice.Service;

import user.userservice.Model.ProfileDetails;

public interface ProfileDetailsService {

    ProfileDetails create(ProfileDetails profileDetails) throws Exception;
    ProfileDetails update(ProfileDetails profileDetails) throws Exception;
    void delete(Long id);
    ProfileDetails findOne(Long id);
}
