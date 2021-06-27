package photo.photoservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import photo.photoservice.Model.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image,Long> {

}
