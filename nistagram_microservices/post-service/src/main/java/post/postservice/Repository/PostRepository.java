package post.postservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import post.postservice.Model.Post;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByUsernameOrderByCreatedAtDesc(String username);
    List<Post> findByIdInOrderByCreatedAtDesc(List<Long> ids);

    List<Post> findByLikes(String username);
    List<Post> findByDislikes(String username);

    @Override
    Post getById(Long id);

    //    void delete(Long id);
}

