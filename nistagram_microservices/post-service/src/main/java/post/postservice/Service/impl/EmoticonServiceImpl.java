package post.postservice.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import post.postservice.Model.Emoticon;
import post.postservice.Model.EmoticonType;
import post.postservice.Repository.EmoticonRepository;
import post.postservice.Service.EmoticonService;

@Service
public class EmoticonServiceImpl implements EmoticonService {

    private EmoticonRepository emoticonRepository;

    @Autowired
    public EmoticonServiceImpl(EmoticonRepository emoticonRepository){
        this.emoticonRepository = emoticonRepository;
    }

    @Override
    public Emoticon create(String username, EmoticonType type) {
        Emoticon emoticon = new Emoticon();
        emoticon.setEmoticonType(type);
        emoticon.setUsername(username);
        this.emoticonRepository.save(emoticon);
        return emoticon;
    }
}
