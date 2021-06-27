package post.postservice.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import post.postservice.Model.EmoticonType;
import post.postservice.Model.Post;
import post.postservice.Repository.EmoticonRepository;
import post.postservice.Service.EmoticonService;

import java.util.List;

@Service
public class EmoticonServiceImpl implements EmoticonService {

    @Autowired
    private EmoticonRepository emoticonRepository;

  /*  public List<Post> findByLike(EmoticonType emoticonType) throws Exception{
        List<Post> likedPosts = this.emoticonRepository.getByLike(emoticonType);
        if (emoticonType == emoticonType.LIKE) {
            return likedPosts;
        }
        else if(likedPosts == null) {
            throw new Exception("There are no liked posts.");
        }
        return null;

    }

    public List<Post> findByDislike(EmoticonType emoticonType) throws Exception{
        List<Post> dislikedPosts = this.emoticonRepository.getByDislike(emoticonType);
        if(emoticonType == EmoticonType.DISLIKE){
            return dislikedPosts;
        }
        if(dislikedPosts == null) {
            throw new Exception("There are no disliked posts.");
        }
        return null;
    }*/

}
