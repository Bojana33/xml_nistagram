package user.userservice.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import user.userservice.Model.ProfileDetails;
import user.userservice.Repository.ProfileDetailsRepository;
import user.userservice.Service.ProfileDetailsService;

@Service
public class ProfileDetailsServiceImpl implements ProfileDetailsService {

    private ProfileDetailsRepository profileDetailsRepository;

    @Autowired
    public ProfileDetailsServiceImpl(ProfileDetailsRepository profileDetailsRepository){
        this.profileDetailsRepository = profileDetailsRepository;
    }

    @Override
    public ProfileDetails create(ProfileDetails profileDetails) throws Exception {
        return null;
    }

    @Override
    public ProfileDetails update(ProfileDetails profileDetails) throws Exception {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public ProfileDetails findOne(Long id) {
        return null;
    }
}
