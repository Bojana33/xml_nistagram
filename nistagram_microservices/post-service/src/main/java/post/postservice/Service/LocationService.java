package post.postservice.Service;

import post.postservice.Model.Location;

import java.util.List;

public interface LocationService {

    List<Location> findByName(String name);


}
