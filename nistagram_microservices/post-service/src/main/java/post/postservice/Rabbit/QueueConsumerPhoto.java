//package post.postservice.Rabbit;
//
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import post.postservice.Configuration.RabbitConfiguration;
//import post.postservice.DTO.Album;
//import post.postservice.DTO.Image;
//
//public class QueueConsumerPhoto {
//
//    @RabbitListener(queues = RabbitConfiguration.QUEUE)
//    public void listener(Album album, Image image){
//        System.out.println(album);
//        System.out.println(image);
//    }
//}
