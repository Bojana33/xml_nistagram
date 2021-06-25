package post.postservice.Repository;

import org.springframework.stereotype.Repository;
import post.postservice.Model.Location;

import java.util.List;

@Repository
public interface LocationRepository {

    List<Location> findByName(List<String> names);
}
