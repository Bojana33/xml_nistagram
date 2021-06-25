package post.postservice.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import post.postservice.Model.Location;
import post.postservice.Service.LocationService;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationService locationService;

    public List<Location> findByName(List<String> names) {
        return locationService.findByName(names);
    }
}
