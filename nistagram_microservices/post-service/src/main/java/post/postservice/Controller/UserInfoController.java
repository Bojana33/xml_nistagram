package post.postservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import post.postservice.Service.UserInfoService;

public class UserInfoController {

    @Autowired
    public UserInfoService userInfoService;
}
