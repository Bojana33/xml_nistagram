package photo.photoservice.Rabbit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import photo.photoservice.Configuration.RabbitConfiguration;
import photo.photoservice.Model.Album;
import photo.photoservice.Model.Image;

@RestController
public class QueueProducer {

        private final RabbitTemplate template;

        @Autowired
        public QueueProducer(RabbitTemplate rabbitTemplate){
            super();
            this.template=rabbitTemplate;
        }

        @PostMapping("/produceAlbum")
        public String produceAlbum(@RequestBody Album album){
            System.out.println("Entered in produce");
            template.convertAndSend(RabbitConfiguration.EXCHANGE,RabbitConfiguration.ROUTING_KEY,album);
            System.out.println("Produce finished");
            return "Produce finished" ;
        }

        @PostMapping("/produceImage")
        public String produceImage(@RequestBody Image image){
            System.out.println("Entered in produce");
            template.convertAndSend(RabbitConfiguration.EXCHANGE,RabbitConfiguration.ROUTING_KEY,image);
            System.out.println("Produce finished");
            return "Produce finished" ;
        }

}
