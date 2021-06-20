package gbike;

import gbike.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class RentAndBillingViewViewHandler {


    @Autowired
    private RentAndBillingViewRepository rentAndBillingViewRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenRented_then_CREATE_1 (@Payload Rented rented) {
        try {

            if (!rented.validate()) return;

            // view 객체 생성
            RentAndBillingView rentAndBillingView = new RentAndBillingView();
            // view 객체에 이벤트의 Value 를 set 함
            rentAndBillingView.setRentid(rented.getRentid());
            rentAndBillingView.setUserid(rented.getUserid());
            rentAndBillingView.setBikeid(rented.getBikeid());
            rentAndBillingView.setRentStatus(rented.getStatus());
            rentAndBillingView.setRentStarttime(rented.getStarttime());
            // view 레파지 토리에 save
            rentAndBillingViewRepository.save(rentAndBillingView);
        
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenReturned_then_UPDATE_0(@Payload RegisteredBill registeredBill) {
        try {
            if (!registeredBill.validate()) return;
                // view 객체 조회
            List<RentAndBillingView> rentAndBillingViewList = rentAndBillingViewRepository.findByRentid(registeredBill.getRentid());
            for(RentAndBillingView rentAndBillingView : rentAndBillingViewList){
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                // view 레파지 토리에 save
                rentAndBillingView.setBillid(registeredBill.getBillid());
                rentAndBillingView.setBillingStatus(registeredBill.getStatus());
                // view 레파지 토리에 save
                rentAndBillingViewRepository.save(rentAndBillingView);
            }
            
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenReturned_then_UPDATE_1(@Payload Returned returned) {
        try {
            if (!returned.validate()) return;
                // view 객체 조회
            List<RentAndBillingView> rentAndBillingViewList = rentAndBillingViewRepository.findByRentid(returned.getRentid());
            for(RentAndBillingView rentAndBillingView : rentAndBillingViewList){
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                // view 레파지 토리에 save
                rentAndBillingView.setRentEndTime(returned.getEndtime());
                rentAndBillingView.setRentStatus(returned.getStatus());
                // view 레파지 토리에 save
                rentAndBillingViewRepository.save(rentAndBillingView);
            }
            
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenCalculatedFee_then_UPDATE_2(@Payload CalculatedFee calculatedFee) {
        try {
            if (!calculatedFee.validate()) return;
                // view 객체 조회
            List<RentAndBillingView> rentAndBillingViewList = rentAndBillingViewRepository.findByRentid(calculatedFee.getRentid());
            for(RentAndBillingView rentAndBillingView : rentAndBillingViewList){
                    rentAndBillingView.setBillid(calculatedFee.getBillid());
                    rentAndBillingView.setFee(calculatedFee.getFee());
                    rentAndBillingView.setBillingStatus(calculatedFee.getStatus());
                // view 레파지 토리에 save
                rentAndBillingViewRepository.save(rentAndBillingView);
            }
            
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenClosedBill_then_UPDATE_3(@Payload ClosedBill closedBill) {
        try {
            if (!closedBill.validate()) return;
                // view 객체 조회
            List<RentAndBillingView> rentAndBillingViewList = rentAndBillingViewRepository.findByRentid(closedBill.getRentid());
            for(RentAndBillingView rentAndBillingView : rentAndBillingViewList){
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                rentAndBillingView.setBillingStatus(closedBill.getStatus());
                // view 레파지 토리에 save
                rentAndBillingViewRepository.save(rentAndBillingView);
            }
            
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenAddedDeposit_then_UPDATE_6(@Payload AddedDeposit addedDeposit) {
        try {
            if (!addedDeposit.validate()) return;
                // view 객체 조회
            List<RentAndBillingView> rentAndBillingViewList = rentAndBillingViewRepository.findByUserid(addedDeposit.getUserid());
            for(RentAndBillingView rentAndBillingView : rentAndBillingViewList){
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                rentAndBillingView.setDeposit(addedDeposit.getDeposit());
                // view 레파지 토리에 save
                rentAndBillingViewRepository.save(rentAndBillingView);
            }
            
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    /*
    @StreamListener(KafkaProcessor.INPUT)
    public void whenCalculatedFee_then_DELETE_1(@Payload CalculatedFee calculatedFee) {
        try {
            if (!calculatedFee.validate()) return;
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    */
}