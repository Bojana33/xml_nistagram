package com.xml.validationrequestservice.Service.impl;

import com.xml.validationrequestservice.Model.VerificationRequest;
import com.xml.validationrequestservice.Repository.VerificationRequestRepository;
import com.xml.validationrequestservice.Service.VerificationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VerificationRequestServiceImpl implements VerificationRequestService {

    private VerificationRequestRepository verificationRequestRepository;

    @Autowired
    private VerificationRequestServiceImpl(VerificationRequestRepository verificationRequestRepository) {
        this.verificationRequestRepository = verificationRequestRepository;
    }


    @Override
    public VerificationRequest create(VerificationRequest verificationRequest) throws Exception {
        if(verificationRequest.getId() != null){
            throw new Exception("Validation request with this Id already exists");
        }
        this.verificationRequestRepository.save(verificationRequest);
        return verificationRequest;
    }

    @Override
    public VerificationRequest update(VerificationRequest verificationRequest) throws Exception {
        VerificationRequest requestToUpdate = this.verificationRequestRepository
                .getById(verificationRequest.getId());
        if(requestToUpdate == null) {
            throw new Exception("Request does not exist");
        }
        requestToUpdate.setCategory(requestToUpdate.getCategory());
        requestToUpdate.setOfficialDocument(verificationRequest.getOfficialDocument());
        this.verificationRequestRepository.save(requestToUpdate);

        return requestToUpdate;
    }

    @Override
    public void Delete(Long id) {
        this.verificationRequestRepository.deleteById(id);
    }

    @Override
    public VerificationRequest findOne(Long id) {
        return this.verificationRequestRepository.getById(id);
    }

    @Override
    public List<VerificationRequest> findAll() {
        return this.verificationRequestRepository.findAll();
    }
}
