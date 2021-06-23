package user.userservice.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import user.userservice.Repository.ProfileDetailsRepository;
import user.userservice.Service.ProfileDetailsService;

@Service
public class ProfileDetailsServiceImpl implements ProfileDetailsService {

    private ProfileDetailsRepository profileDetailsRepository;

    @Autowired
    public ProfileDetailsServiceImpl(ProfileDetailsRepository profileDetailsRepository){
        this.profileDetailsRepository = profileDetailsRepository;
    }

}
