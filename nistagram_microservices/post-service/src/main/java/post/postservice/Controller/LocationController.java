package post.postservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import post.postservice.Model.Location;
import post.postservice.Service.LocationService;

import java.util.List;

@RestController
@RequestMapping(value = "/")
public class LocationController {

    @Autowired
    public LocationService locationService;

    @GetMapping(value = "/findByName")
    public ResponseEntity<?> findByName(@PathVariable("name") List<String> names) {
        List<Location> locations = locationService.findByName(names);
        return ResponseEntity.ok(locations);
    }

}
