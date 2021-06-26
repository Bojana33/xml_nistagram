package user.userservice.Service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import user.userservice.Model.Profile;
import user.userservice.Repository.ProfileRepository;
import user.userservice.Service.ProfileService;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProfileServiceImpl implements ProfileService {

    private ProfileRepository profileRepository;

    @Autowired
    public ProfileServiceImpl(ProfileRepository profileRepository){
        this.profileRepository = profileRepository;
    }

    @Override
    public Profile create(Profile profile) throws Exception {
        if(profile.getId() != null){
            throw new Exception("Profile with this Id already exist");
        }
        this.profileRepository.save(profile);
        return profile;
    }

    @Override
    public Profile update(Profile profile) throws Exception {
        Profile profileToUpdate = this.profileRepository.getById(profile.getId());
        if(profileToUpdate==null){
            throw new Exception("Profile doesn't exist");
        }
        profileToUpdate.setProfilePicture(profile.getProfilePicture());
        profileToUpdate.setBiography(profile.getBiography());
        profileToUpdate.setBirthday(profile.getBirthday());
        profileToUpdate.setDisplayName(profile.getDisplayName());
        profileToUpdate.setWebsite(profile.getWebsite());
        profileToUpdate.setVerified(profile.getVerified());
        profileToUpdate.setPrivateProfile(profile.getPrivateProfile());
        profileToUpdate.setDeactivated(profile.getDeactivated());

        this.profileRepository.save(profileToUpdate);

        return profileToUpdate;
    }

    @Override
    public void delete(Long id) {
        this.profileRepository.deleteById(id);
    }

    @Override
    public Profile findOne(Long id) {
        Profile profile = this.profileRepository.getById(id);
        return profile;
    }

    @Override
    public List<Profile> findAll() {
        List<Profile> allProfiles = this.profileRepository.findAll();
        return allProfiles;
    }

    public Optional<Profile> findByDisplayName(String displayname) {
        log.info("retrieving user {}", displayname);
        return profileRepository.findByDisplayName(displayname);
    }
}
