package photo.photoservice.Service;

import photo.photoservice.Model.Tag;

import java.util.List;

public interface TagService {

    List<Tag> findByName(String names);
    //Tag findByUsernameAndImageId(String username, Long photoId);
}
