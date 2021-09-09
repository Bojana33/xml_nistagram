package photo.photoservice.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import photo.photoservice.Repository.ImageRepository;
import photo.photoservice.Service.ImageService;

@Service
public class ImageServiceImpl implements ImageService {
    private ImageRepository imageRepository;

    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository) {this.imageRepository=imageRepository;}
}
