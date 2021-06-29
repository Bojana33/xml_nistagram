package post.postservice.Service;

import org.springframework.web.multipart.MultipartFile;
import post.postservice.Model.EmoticonType;
import post.postservice.Model.Post;

import java.util.List;

public interface PostService {

  //  Post create(Post post) throws Exception;

    Post update(Post post) throws Exception;

    void delete(Long id) throws Exception;

    Post create(Post post);

    Post savePost(Post post, MultipartFile file,String caption, String username);

    Post findOne(Long id) throws Exception;

    List<Post> postsByUsername(String username);

    List<Post> postsByIdIn(List<Long> ids);

    List<Post> findByLike(EmoticonType emoticonType, String username) throws Exception;

    List<Post> findByDislike(EmoticonType emoticonType, String username) throws Exception;
}
