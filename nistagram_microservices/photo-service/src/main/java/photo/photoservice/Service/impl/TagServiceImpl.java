package photo.photoservice.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import photo.photoservice.Repository.TagRepository;
import photo.photoservice.Service.TagService;

@Service
public class TagServiceImpl implements TagService {
    private TagRepository tagRepository;

    @Autowired
    public TagServiceImpl(TagRepository tagRepository){this.tagRepository=tagRepository;}
}
