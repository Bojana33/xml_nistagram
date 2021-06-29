package com.xml.validationrequestservice.Repository;

import com.xml.validationrequestservice.Model.VerificationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VerificationRequestRepository extends JpaRepository<VerificationRequest, Long> {

    @Override
    VerificationRequest getById(Long id);

    @Override
    List<VerificationRequest> findAll();
}
