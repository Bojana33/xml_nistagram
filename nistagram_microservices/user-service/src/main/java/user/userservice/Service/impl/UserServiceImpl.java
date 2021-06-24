package user.userservice.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import user.userservice.Model.User;
import user.userservice.Repository.UserRepository;
import user.userservice.Service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) throws Exception {
        if(user.getId() != null){
            throw new Exception("User with that Id already exist");
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

        this.userRepository.save(userToUpdate);

        return userToUpdate;
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
}
