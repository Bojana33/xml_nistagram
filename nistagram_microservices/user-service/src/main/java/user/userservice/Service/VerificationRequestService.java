package user.userservice.Service;


import user.userservice.Model.VerificationRequest;

import java.util.List;

public interface VerificationRequestService {

    VerificationRequest create(VerificationRequest verificationRequest) throws Exception;
    VerificationRequest update(VerificationRequest verificationRequest) throws Exception;
    void delete(Long id);
    VerificationRequest findOne(Long id);
    List<VerificationRequest> findAll();

}
