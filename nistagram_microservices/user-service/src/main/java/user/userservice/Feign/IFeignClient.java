package user.userservice.Feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value="notification-service", url="http://localhost:8086/notification")
public interface IFeignClient {
    @GetMapping(path = "/feign")
    ResponseEntity<String> testMessage();
}
