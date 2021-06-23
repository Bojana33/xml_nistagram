package photo.photoservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import photo.photoservice.Model.Album;

@Repository
public interface AlbumRepository extends JpaRepository<Album,Long> {
}
