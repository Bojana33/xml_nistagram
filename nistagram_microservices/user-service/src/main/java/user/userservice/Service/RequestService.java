package user.userservice.Service;

import user.userservice.Model.Request;

public interface RequestService {

    Request create(Request request);
    Request findOne(Long id);
    Request update(Request request) throws Exception;
    void delete(Long id);

}
