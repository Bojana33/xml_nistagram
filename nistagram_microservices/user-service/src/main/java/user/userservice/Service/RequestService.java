package user.userservice.Service;

import user.userservice.Model.Request;

public interface RequestService {

    Request create(Request request);
    Request findOne(Long id);

}
