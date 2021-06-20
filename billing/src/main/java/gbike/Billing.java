package gbike;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;

import java.util.Date;

@Entity
@Table(name="Billing_table")
public class Billing {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long billid;
    private Long rentid;
    private Long userid;
    private Date starttime;
    private Date endtime;
    private Integer fee;
    private String status;

    @PostPersist
    public void onPostPersist(){
        RegisteredBill registeredBill = new RegisteredBill();
        BeanUtils.copyProperties(this, registeredBill);
        registeredBill.publishAfterCommit();


    }

    @PostUpdate
    public void onPostUpdate(){
        //CLOSEBILL일 경우만 보냄
        if("CLOSEBILL".equals(this.getStatus())){
            ClosedBill closedBill = new ClosedBill();
            BeanUtils.copyProperties(this, closedBill);
            closedBill.publishAfterCommit();
        }


    }

    @PreUpdate
    public void onPreUpdate(){
        //APPLYFEE일 경우만 보냄
        if("APPLYFEE".equals(this.getStatus())){
            CalculatedFee calculatedFee = new CalculatedFee();
            BeanUtils.copyProperties(this, calculatedFee);
            calculatedFee.publishAfterCommit();
        }
    }


    public Long getBillid() {
        return billid;
    }

    public void setBillid(Long billid) {
        this.billid = billid;
    }
    public Long getRentid() {
        return rentid;
    }

    public void setRentid(Long rentid) {
        this.rentid = rentid;
    }
    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }
    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }
    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }
    public Integer getFee() {
        return fee;
    }

    public void setFee(Integer fee) {
        this.fee = fee;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }




}
