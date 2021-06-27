package post.postservice.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import post.postservice.Model.Post;
import post.postservice.Repository.PostRepository;
import post.postservice.Service.PostService;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
/*
    @Override
    public Post create(Post post) throws Exception {
        if (post.getId() != null) {
            throw new Exception("Post with this Id already exist");
        }

        return this.postRepository.save(post);
    }*/
    @Override
    public Post savePost(Post post, MultipartFile file, String caption, String username)
    {

        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        if (filename.contains(".."))
        {
            System.out.println("Not a valid file");
        }
        try {
            post.setImageUrl(Base64.getEncoder().encodeToString(file.getBytes()));
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
        postToUpdate.setImageUrl(post.getImageUrl());

        this.postRepository.save(postToUpdate);

        return postToUpdate;
    }
    @Override
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
}
