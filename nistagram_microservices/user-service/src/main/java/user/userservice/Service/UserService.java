package user.userservice.Service;

import user.userservice.Model.User;

public interface UserService {

    User create(User user) throws Exception;
    User update(User user) throws Exception;
    void delete(Long id);
    User findOne(Long id);
}
