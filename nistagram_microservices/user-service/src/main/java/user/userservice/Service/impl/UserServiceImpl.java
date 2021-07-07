package user.userservice.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import user.userservice.Model.Request;
import user.userservice.Model.User;
import user.userservice.Repository.RequestRepository;
import user.userservice.Repository.UserRepository;
import user.userservice.Service.RequestService;
import user.userservice.Service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RequestService requestService;
    private RequestRepository requestRepository;

    @Autowired
    public UserServiceImpl(RequestRepository requestRepository, UserRepository userRepository, RequestService requestService){
        this.userRepository = userRepository;
        this.requestService = requestService;
        this.requestRepository = requestRepository;
    }

    @Override
    public User create(User user) throws Exception {
        if(user.getId() != null){
            throw new Exception("User with this Id already exist");
        }
        this.userRepository.save(user);
        return user;
    }

    @Override
    public User update(User user) throws Exception {
        User userToUpdate = this.userRepository.getById(user.getId());
        if (userToUpdate == null){
            throw new Exception("User doesn't exist");
        }
        userToUpdate.setActive(user.getActive());
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setGender(user.getGender());
        userToUpdate.setPassword(user.getPassword());
        userToUpdate.setPhone(user.getPhone());
        userToUpdate.setUsername(user.getUsername());
        userToUpdate.setRole(user.getRole());
        userToUpdate.setName(user.getName());
        userToUpdate.setLastname(user.getLastname());
        userToUpdate.setWebsite(user.getWebsite());
        userToUpdate.setDisplayName(user.getDisplayName());
        userToUpdate.setProfilePicture(user.getProfilePicture());
        userToUpdate.setBirthday(user.getBirthday());
        userToUpdate.setBiography(user.getBiography());

        this.userRepository.save(userToUpdate);

        return userToUpdate;
    }

    @Override
    public User privacySettings(String username, Boolean tagMe, Boolean messagesFromUnfollowers, Boolean privateProfile) throws Exception{
        User userToUpdate = this.userRepository.findByUsername(username);
        if (userToUpdate == null){
            throw new Exception("User doesn't exist");
        }
        userToUpdate.setMessagesFromUnfollowers(messagesFromUnfollowers);
        userToUpdate.setTagMe(tagMe);
        userToUpdate.setPrivateProfile(privateProfile);
        this.userRepository.save(userToUpdate);

        return userToUpdate;
    }

    @Override
    public void blockUser(String usernameBlocks, String usernameToBlock) {
        User userBlocks = this.userRepository.findByUsername(usernameBlocks);
        userBlocks.getBlockedProfiles().add(usernameToBlock);
        this.userRepository.save(userBlocks);
    }

    @Override
    public User notificationSettings(String username, Boolean messageNotification, Boolean postNotification, Boolean commentNotification,Boolean followNotification) throws Exception {
        User userToUpdate = this.userRepository.findByUsername(username);
        if (userToUpdate == null){
            throw new Exception("User doesn't exist");
        }
        userToUpdate.setCommentNotification(commentNotification);
        userToUpdate.setPostNotification(postNotification);
        userToUpdate.setFollowNotification(followNotification);
        userToUpdate.setMessageNotification(messageNotification);
        this.userRepository.save(userToUpdate);
        return userToUpdate;
    }

    public void followUser(String sender, String receiver) {
        User user = this.userRepository.findByUsername(receiver);
        User senderFollowers = this.userRepository.findByUsername(sender);
        if (user.getPrivateProfile() == false) {
            senderFollowers.getFollowers().add(receiver);
            this.userRepository.save(senderFollowers);
            //bidirekciono
            user.getFollowers().add(sender);
        } else{
            Request request = new Request();
            request.setReceiver(user);
            request.setSender(senderFollowers);
            request.setAccepted(false);
            this.requestService.create(request);
            user.getReceivedRequests().add(request);
        }
        this.userRepository.save(user);
    }

    public void handleRequests(String receiver, String sender, Long id, Boolean status) {
        Request request = this.requestService.findOne(id);
        request.setAccepted(status);
        User receiverFollowers = this.userRepository.findByUsername(receiver);
        User senderFollowers = this.userRepository.findByUsername(sender);
        this.requestRepository.save(request);
        if (request.getAccepted()) {
            receiverFollowers.getFollowers().add(sender);
            this.requestRepository.deleteById(id);
            this.userRepository.save(receiverFollowers);
            //bidirekciono
            senderFollowers.getFollowers().add(receiver);
            this.userRepository.save(senderFollowers);
        }
        else{
            this.requestRepository.deleteById(id);
        }
    }

    @Override
    public void delete(Long id) {
        this.userRepository.deleteById(id);
    }

    @Override
    public User findOne(Long id) {
        User user = this.userRepository.getById(id);
        return user;
    }

    @Override
    public User findByUsername(String username) {
        User user = this.userRepository.findByUsername(username);
        return user;

    }

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }
}
