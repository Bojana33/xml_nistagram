package post.postservice.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import post.postservice.Model.Post;
import post.postservice.Repository.PostRepository;
import post.postservice.Service.PostService;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post create(Post post) throws Exception {
        if (post.getId() != null) {
            throw new Exception("Post with this Id already exist");
        }
            post = this.postRepository.save(post);
            return post;
    }

    public Post update(Post post) throws Exception{
        Post postToUpdate = this.postRepository.getById(post.getId());
        if (postToUpdate == null) {
            throw new Exception("Post doesn't exist.");
        }
        postToUpdate.setId(post.getId());

        this.postRepository.save(postToUpdate);

        return postToUpdate;
    }
    @Override
    public void delete(Long id){
        this.postRepository.delete(id);
    }

    public List<Post> postsByUsername(String username){return postRepository.findByUsernameOrderByCreatedAtDesc(username);}
    public List<Post> postsByIdIn(List<Long> ids){return postRepository.findByIdInOrderByCreatedAtDesc(ids);}
}
