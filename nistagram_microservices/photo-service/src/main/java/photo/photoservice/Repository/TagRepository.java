package photo.photoservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import photo.photoservice.Model.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
}
