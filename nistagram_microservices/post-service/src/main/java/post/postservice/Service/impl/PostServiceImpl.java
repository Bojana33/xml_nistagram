package post.postservice.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import post.postservice.DTO.Image;
import post.postservice.Model.EmoticonType;
import post.postservice.Model.Post;
import post.postservice.Repository.PostRepository;
import post.postservice.Service.PostService;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
    public Post savePost(Post post, MultipartFile file, String caption, String username, Image image)
    {

        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        if (filename.contains(".."))
        {
            System.out.println("Not a valid file");
        }
        try {
            image.setUri(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        post.setCaption(caption);
        post.setUsername(username);
        post.setCreatedAt(new Date());
        post.setUpdatedAt(new Date());

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

    public void delete(Long id) throws Exception {
        Post post = this.postRepository.getById(id);
        this.postRepository.delete(post);
    }

    public List<Post> postsByUsername(String username) {
        return postRepository.findByUsernameOrderByCreatedAtDesc(username);
    }

    @Override
    public Post findOne(Long id)
    {
        Post post = this.postRepository.getById(id);
        return post;
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
