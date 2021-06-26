package photo.photoservice.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import photo.photoservice.Model.Tag;
import photo.photoservice.Repository.TagRepository;
import photo.photoservice.Service.TagService;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    private TagRepository tagRepository;

    @Autowired
    public TagServiceImpl(TagRepository tagRepository){this.tagRepository=tagRepository;}

    public List<Tag> findByName(String name) {
        return tagRepository.findByName(name);
    }
}
