package post.postservice.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import post.postservice.Repository.UserInfoRepository;
import post.postservice.Service.UserInfoService;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    private UserInfoRepository userInfoRepository;

    @Autowired
    public UserInfoServiceImpl(UserInfoRepository userInfoRepository)
    {
        this.userInfoRepository = userInfoRepository;
    }
}
