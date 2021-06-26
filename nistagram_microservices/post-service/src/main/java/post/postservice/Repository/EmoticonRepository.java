package post.postservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import post.postservice.Model.Emoticon;
import post.postservice.Model.EmoticonType;
import post.postservice.Model.Post;

import java.util.List;

@Repository
public interface EmoticonRepository extends JpaRepository<Emoticon, EmoticonType> {

    List<Post> getByLike(EmoticonType emoticonType);


    List<Post> getByDislike(EmoticonType emoticonType);
}
