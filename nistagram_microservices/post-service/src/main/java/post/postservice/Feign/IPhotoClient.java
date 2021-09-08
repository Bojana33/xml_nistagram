package post.postservice.Feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "photo-service", url = "http://localhost:8087/photo")
public interface IPhotoClient {

}
