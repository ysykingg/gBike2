package gbike;

import gbike.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
    @Autowired BikeRepository bikeRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverReturned_UpdateStatusAndLoc(@Payload Returned returned){

        if(returned.isMe()){
            
            Bike bike = bikeRepository.findByBikeid(Long.valueOf(returned.getBikeid()));
            
            //bike.setStatus(returned.getStatus());
            bike.setStatus("사용가능");
            bike.setLocation(returned.getEndlocation());
            
            bikeRepository.save(bike);
        }
            
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}


}
