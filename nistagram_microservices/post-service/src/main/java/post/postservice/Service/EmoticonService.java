package post.postservice.Service;

import post.postservice.Model.Emoticon;
import post.postservice.Model.EmoticonType;

public interface EmoticonService {

    Emoticon create(String username, EmoticonType type);
}
