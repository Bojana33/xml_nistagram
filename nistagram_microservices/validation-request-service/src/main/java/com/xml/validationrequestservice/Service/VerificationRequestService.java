package com.xml.validationrequestservice.Service;

import com.xml.validationrequestservice.Model.VerificationRequest;

import java.util.List;

public interface VerificationRequestService {

    VerificationRequest create(VerificationRequest user) throws Exception;
    VerificationRequest update(VerificationRequest user) throws Exception;
    void Delete(Long id);
    VerificationRequest findOne(Long id);
    public List<VerificationRequest> findAll();
}
