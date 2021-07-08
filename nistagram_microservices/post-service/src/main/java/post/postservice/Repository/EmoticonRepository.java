package post.postservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import post.postservice.Model.Emoticon;

@Repository
public interface EmoticonRepository extends JpaRepository<Emoticon, Long> {
}
