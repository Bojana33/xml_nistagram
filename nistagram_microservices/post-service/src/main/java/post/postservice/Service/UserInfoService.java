package post.postservice.Service;

import post.postservice.DTO.User;

public interface UserInfoService {

    public User findByUsername(String un);

    public User findById(Long id);


}
