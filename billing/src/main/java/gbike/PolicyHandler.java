package gbike;

import gbike.config.kafka.KafkaProcessor;

import java.util.Date;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
    @Autowired BillingRepository billingRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverRented_RegisterBill(@Payload Rented rented){

        if(!rented.validate()) return;

        System.out.println("\n\n##### listener RegisterBill : " + rented.toJson() + "\n\n");
        
        // Sample Logic //
        Billing billing = new Billing();
        billing.setRentid(rented.getRentid());
        billing.setUserid(rented.getUserid());
        billing.setStarttime(rented.getStarttime());
        billing.setStatus("OPENBILL");
        billingRepository.save(billing);
            
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverReturned_CalculateFee(@Payload Returned returned){

        if(!returned.validate()) return;

        System.out.println("\n\n##### listener CalculateFee : " + returned.toJson() + "\n\n");

        // Sample Logic //

        Billing billing = billingRepository.findByRentid(Long.valueOf(returned.getRentid()));
        
		Date d1 = returned.getEndtime();
		Date d2 = billing.getStarttime();
		long diff = d1.getTime() - d2.getTime();
		Integer min = (int) (diff / 60000);
        Integer calFee = min * 100;

        billing.setFee(calFee);
        billing.setEndtime(returned.getEndtime());
        billing.setStatus("APPLYFEE");
        billingRepository.save(billing);
            
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverUpdatedDeposit_CloseBill(@Payload UpdatedDeposit updatedDeposit){

        if(!updatedDeposit.validate()) return;

        System.out.println("\n\n##### listener CloseBill : " + updatedDeposit.toJson() + "\n\n");

        // Sample Logic //
        Billing billing = billingRepository.findByBillid(updatedDeposit.getBillid());
        
        //user정보 최초등록 시 CLOSEBILL처리 무시
        if(billing != null){
            billing.setStatus("CLOSEBILL");
            billingRepository.save(billing);
        }
            
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}


}
