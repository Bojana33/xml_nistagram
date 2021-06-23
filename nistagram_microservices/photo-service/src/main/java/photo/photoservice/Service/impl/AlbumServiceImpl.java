package photo.photoservice.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import photo.photoservice.Repository.AlbumRepository;
import photo.photoservice.Service.AlbumService;

@Service
public class AlbumServiceImpl extends AlbumService {
    private AlbumRepository albumRepository;

    @Autowired

}
