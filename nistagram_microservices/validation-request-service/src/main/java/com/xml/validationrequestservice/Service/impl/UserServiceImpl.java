package com.xml.validationrequestservice.Service.impl;

import com.xml.validationrequestservice.Model.User;
import com.xml.validationrequestservice.Repository.UserRepository;
import com.xml.validationrequestservice.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) throws Exception {
        if(user.getUsername() != null){
            throw new Exception("User with this username already exist");
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
        userToUpdate.setBiography(user.getBiography());
        userToUpdate.setUsername(user.getUsername());
        userToUpdate.setFirstName(user.getFirstName());
        userToUpdate.setLastName(user.getLastName());
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

    @Override
    public List<User> findAll(){
        return this.userRepository.findAll();
    }

}
