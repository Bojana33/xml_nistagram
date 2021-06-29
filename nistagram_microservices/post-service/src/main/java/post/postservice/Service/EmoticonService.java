package post.postservice.Service;

import post.postservice.Model.EmoticonType;
import post.postservice.Model.Post;
import post.postservice.Model.UserInfo;

import java.util.List;

public interface EmoticonService {

    List<Post> findByLike(EmoticonType emoticonType, UserInfo userInfo) throws Exception;

    List<Post> findByDislike(EmoticonType emoticonType, UserInfo userInfo) throws Exception;

}
