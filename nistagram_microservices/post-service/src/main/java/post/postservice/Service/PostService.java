package post.postservice.Service;

import post.postservice.Model.Post;

import java.util.List;

public interface PostService {

    Post create(Post post) throws Exception;

    Post update(Post post) throws Exception;

//    void delete(Long id, String username) throws Exception;
//
//    List<Post> postsByUsername(String username);
//
//    List<Post> postsByIdIn(List<Long> ids);
}
