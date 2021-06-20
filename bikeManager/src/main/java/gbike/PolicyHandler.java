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
    @Autowired BikeManagerRepository bikeManagerRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverReported_AdjustedPoint(@Payload Reported reported){

        if(!reported.validate()) return;

        System.out.println("\n\n##### listener AddPoint : " + reported.toJson() + "\n\n");

        Long managerid = reported.getManagerid();
        
        BikeManager bikeManager = bikeManagerRepository.findByManagerid(managerid);
        Long oldPoint = bikeManager.getPoint();
        
        //한건 보고 당 1000원 Point 적립
        bikeManager.setPoint(oldPoint + 1000);
        bikeManagerRepository.save(bikeManager);
            
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}


}
