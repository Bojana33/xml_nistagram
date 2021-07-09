package post.postservice.Payload;

import lombok.Data;

@Data
public class PostRequest {
    private String imageUrl1;
    private String imageUrl2;
    private String imageUrl3;
    private String caption;
    private String username;
}
