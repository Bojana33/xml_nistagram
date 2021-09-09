package user.userservice.Service;

import org.apache.logging.log4j.message.Message;
import user.userservice.Model.Request;
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
    void unblockUser(String usernameUnblocking, String usernameToUnblock);
    User notificationSettings(String username, Boolean messageNotification, Boolean postNotification, Boolean commentNotification,Boolean followNotification) throws Exception;
    void followUser(String sender, String receiver);
    void handleRequests(String receiver, String sender, Long id, Boolean status) throws Exception;
    void unfollowUser(String username, String usernameToUnfolow);

    User findByUsernameAndPassword(String username, String password);

}
