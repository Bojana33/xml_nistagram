package com.xml.validationrequestservice.Service;

import com.xml.validationrequestservice.Model.User;

import java.util.List;

public interface UserService {

    User create(User user) throws Exception;
    User update(User user) throws Exception;
    void delete(Long id);
    User findOne(Long id);
    public List<User> findAll();

}
