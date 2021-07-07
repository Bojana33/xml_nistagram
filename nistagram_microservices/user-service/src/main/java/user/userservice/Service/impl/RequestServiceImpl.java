package user.userservice.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import user.userservice.Model.Request;
import user.userservice.Repository.RequestRepository;
import user.userservice.Service.RequestService;

@Service
public class RequestServiceImpl implements RequestService {

    private RequestRepository requestRepository;

    @Autowired
    public RequestServiceImpl(RequestRepository requestRepository){
        this.requestRepository = requestRepository;
    }

    @Override
    public Request create(Request request){
        this.requestRepository.save(request);
        return request;
    }

    @Override
    public Request findOne(Long id) {
        Request request = this.requestRepository.getById(id);
        return request;
    }

}
