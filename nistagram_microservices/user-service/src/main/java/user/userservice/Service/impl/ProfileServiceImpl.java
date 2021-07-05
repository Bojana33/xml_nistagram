package user.userservice.Service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import user.userservice.Model.Profile;
import user.userservice.Repository.ProfileRepository;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProfileServiceImpl {

    private ProfileRepository profileRepository;

    @Autowired
    public ProfileServiceImpl(ProfileRepository profileRepository){
        this.profileRepository = profileRepository;
    }

    public Profile create(Profile profile) throws Exception {
        if(profile.getId() != null){
            throw new Exception("Profile with this Id already exist");
        }
        this.profileRepository.save(profile);
        return profile;
    }

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

    public void delete(Long id) {
        this.profileRepository.deleteById(id);
    }

    public Profile findOne(Long id) {
        Profile profile = this.profileRepository.getById(id);
        return profile;
    }

    public List<Profile> findAll() {
        List<Profile> allProfiles = this.profileRepository.findAll();
        return allProfiles;
    }

    public Optional<Profile> findByDisplayName(String displayname) {
        log.info("retrieving user {}", displayname);
        return profileRepository.findByDisplayName(displayname);
    }
}
