package gbike;

import gbike.config.kafka.KafkaProcessor;

import java.util.Optional;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
    @Autowired UserDepositRepository userDepositRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverCalculatedFee_UpdateDeposit(@Payload CalculatedFee calculatedFee){

        if(!calculatedFee.validate()) return;

        System.out.println("\n\n##### listener UpdateDeposit : " + calculatedFee.toJson() + "\n\n");

        // Sample Logic //
        UserDeposit userDeposit = userDepositRepository.findByUserid(Long.valueOf(calculatedFee.getUserid()));
        
        Integer iDeposit = userDeposit.getDeposit();
        Integer iFee = calculatedFee.getFee();
        userDeposit.setDeposit(iDeposit - iFee);
        userDeposit.setBillid(calculatedFee.getBillid());
        
        userDepositRepository.save(userDeposit);
            
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverAdjustedPoint_AddDeposit(@Payload AdjustedPoint adjustedPoint){

        if(!adjustedPoint.validate()) return;

        System.out.println("\n\n##### listener AddDeposit : " + adjustedPoint.toJson() + "\n\n");

        // Sample Logic //
        UserDeposit userDeposit = userDepositRepository.findByUserid(Long.valueOf(adjustedPoint.getUserid()));
        
        Integer AdjustPoint = Long.valueOf(Optional.ofNullable(adjustedPoint.getAdjustpoint()).orElse(0L)).intValue();
        Integer lUserDeposit = userDeposit.getDeposit();
        
        //Point -> Deposit으로 추가적립
        userDeposit.setDeposit(lUserDeposit + AdjustPoint);

        userDepositRepository.save(userDeposit);
            
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}


}
