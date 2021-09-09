package post.postservice.Rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import post.postservice.Configuration.RabbitConfiguration;
import post.postservice.DTO.User;

@Component
public class QueueConsumerUser {

    @RabbitListener(queues = RabbitConfiguration.QUEUE)
    public User findUserByYsername(User user){
        return user;
    }

}
