package user.userservice.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import user.userservice.Model.VerificationRequest;
import user.userservice.Repository.VerificationRequestRepository;
import user.userservice.Service.VerificationRequestService;

import java.util.List;

@Service
public class VerificationRequestServiceImpl implements VerificationRequestService {

    private VerificationRequestRepository verificationRequestRepository;

    @Autowired
    public VerificationRequestServiceImpl(VerificationRequestRepository verificationRequestRepository) {
        this.verificationRequestRepository = verificationRequestRepository;
    }

    @Override
    public VerificationRequest create(VerificationRequest verificationRequest) throws Exception {
        if(verificationRequest.getId() != null){
            throw new Exception("Verification request with this Id already exist");
        }
        this.verificationRequestRepository.save(verificationRequest);
        return verificationRequest;
    }

    @Override
    public VerificationRequest update(VerificationRequest verificationRequest) throws Exception {
        VerificationRequest requestToUpdate = this.verificationRequestRepository.getById(verificationRequest.getId());
        if(requestToUpdate == null){
            throw new Exception("Verification request doesn't exist");
        }
        requestToUpdate.setCategory(verificationRequest.getCategory());
        requestToUpdate.setAccepted(verificationRequest.getAccepted());
        requestToUpdate.setOfficialDocument(verificationRequest.getOfficialDocument());
        requestToUpdate.setVerification_sender(verificationRequest.getVerification_sender());

        this.verificationRequestRepository.save(requestToUpdate);

        return requestToUpdate;
    }

    @Override
    public void delete(Long id) { this.verificationRequestRepository.deleteById(id); }

    @Override
    public VerificationRequest findOne(Long id) {
        VerificationRequest verificationRequest = this.verificationRequestRepository.getById(id);
        return verificationRequest;
    }

    @Override
    public List<VerificationRequest> findAll() { return this.verificationRequestRepository.findAll(); }
}
