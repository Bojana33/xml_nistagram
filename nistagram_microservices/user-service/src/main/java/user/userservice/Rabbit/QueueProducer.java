package user.userservice.Rabbit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import user.userservice.Configuration.RabbitConfiguration;
import user.userservice.Model.User;

@RestController
public class QueueProducer {

    private final RabbitTemplate template;

    @Autowired
    public QueueProducer(RabbitTemplate rabbitTemplate){
        super();
        this.template=rabbitTemplate;
    }

    @PostMapping("/produce")
    public String produce(@RequestBody User user){
        System.out.println("Entered in produce");
        template.convertAndSend(RabbitConfiguration.EXCHANGE,RabbitConfiguration.ROUTING_KEY,user);
        System.out.println("Produce finished");
        return "Produce finished" ;
    }
}
