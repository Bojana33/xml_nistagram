package post.postservice.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import post.postservice.Model.EmoticonType;
import post.postservice.Model.Post;
import post.postservice.Payload.PostRequest;
import post.postservice.Repository.PostRepository;
import post.postservice.Service.PostService;


import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;
    private EmoticonServiceImpl emoticonServiceImpl;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, EmoticonServiceImpl emoticonServiceImpl) {
        this.postRepository = postRepository;
        this.emoticonServiceImpl = emoticonServiceImpl;
    }

    @Override
    public Post savePost(PostRequest postRequest)
    {

        Post post = new Post(postRequest.getCaption(), postRequest.getImageUrl1());

        return this.postRepository.save(post);
    }

    @Override
    public Post create(Post post)
    {
        return this.postRepository.save(post);
    }

    @Override
    public Post update(Post post) throws Exception {
        Post postToUpdate = this.postRepository.getById(post.getId());
        if (postToUpdate == null) {
            throw new Exception("Post doesn't exist.");
        }
        postToUpdate.setId(post.getId());
        postToUpdate.setCaption(post.getCaption());
        postToUpdate.setCreatedAt(post.getCreatedAt());
        postToUpdate.setUpdatedAt(post.getUpdatedAt());
        postToUpdate.setUsername(post.getUsername());
        //postToUpdate.setImageUrl(post.getImageUrl());

        this.postRepository.save(postToUpdate);

        return postToUpdate;
    }

    public void delete(Long id){
        Post post = this.postRepository.getById(id);
        this.postRepository.delete(post);
    }

    public List<Post> postsByUsername(String username) {
        return postRepository.findByUsernameOrderByCreatedAtDesc(username);
    }

    @Override
    public Post findOne(Long id)
    {
        return this.postRepository.getById(id);
    }

    @Override
    public List<Post> postsByIdIn(List<Long> ids) {
        return postRepository.findByIdInOrderByCreatedAtDesc(ids);
    }


    public List<Post> findByEmoticons(EmoticonType emoticonType, String username) throws Exception{
        List<Post> likedPosts = this.postRepository.findByLikes(username);
        List<Post> dislikedPosts = this.postRepository.findByDislikes(username);
        if (emoticonType == emoticonType.LIKE) {
            if(likedPosts == null) {
                throw new Exception("There are no liked posts.");
            }
            return likedPosts;
        } else {
            if(dislikedPosts == null) {
                throw new Exception("There are no disliked posts.");
            }
            return dislikedPosts;
        }
    }

    @Override
    public void likePost(String username, Long id) {
        Post post = this.postRepository.getById(id);
        post.getLikes().add(username);
        emoticonServiceImpl.create(username,EmoticonType.LIKE);
        this.postRepository.save(post);
    }

    @Override
    public void dislikePost(String username, Long id) {
        Post post = this.postRepository.getById(id);
        post.getDislikes().add(username);
        emoticonServiceImpl.create(username,EmoticonType.DISLIKE);
        this.postRepository.save(post);
    }

//    public void delete(Long id, String username) throws Exception {
//        Post post = this.postRepository.getById(id);
//        if (post.getUsername() != username) {
//            throw new Exception("You can't delete this post.");
//        }
//        if (post == null) {
//            throw new Exception("Post doesn't exist.");
//        }
//    }
//
//    public List<Post> postsByUsername(String username){return postRepository.findByUsernameOrderByCreatedAtDesc(username);}
//    public List<Post> postsByIdIn(List<Long> ids){return postRepository.findByIdInOrderByCreatedAtDesc(ids);}
}
