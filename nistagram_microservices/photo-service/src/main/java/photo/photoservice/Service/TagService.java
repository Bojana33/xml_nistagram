package photo.photoservice.Service;

import org.springframework.stereotype.Service;
import photo.photoservice.Model.Tag;

import java.util.List;

public interface TagService {

    List<Tag> findByName(List<String> names);
}
