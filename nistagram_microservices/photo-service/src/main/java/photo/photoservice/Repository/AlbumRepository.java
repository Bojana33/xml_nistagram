package photo.photoservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import photo.photoservice.Model.Album;

import java.util.List;

@Repository
public interface AlbumRepository extends JpaRepository<Album,Long> {

}
