package user.userservice.Service;

import user.userservice.Model.User;

import java.util.List;

public interface UserService {

    User create(User user) throws Exception;
    User update(User user) throws Exception;
    void delete(Long id);
    User findOne(Long id);
    User findByUsername(String username);
    List<User> findAll();
    User privacySettings(String username, Boolean tagMe, Boolean messagesFromUnfollowers, Boolean privateProfile) throws Exception;
    void blockUser(String usernameBlocks, String usernameToBlock);
    User notificationSettings(String username, Boolean messageNotification, Boolean postNotification, Boolean commentNotification,Boolean followNotification) throws Exception;


}
