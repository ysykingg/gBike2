package gbike;

import javax.persistence.*;
import java.util.List;
import java.util.Date;

@Entity
@Table(name="RentAndBillingView_table")
public class RentAndBillingView {

        @Id
        //@GeneratedValue(strategy=GenerationType.AUTO)
        //private Long id;
        private Long rentid;
        private Long userid;
        private Long bikeid;
        private String rentStatus;
        private Date rentStarttime;
        private Date rentEndTime;
        private Long billid;
        private Integer fee;
        private String billingStatus;
        private Integer deposit;

/*
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
*/
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
        public Long getBikeid() {
            return bikeid;
        }

        public void setBikeid(Long bikeid) {
            this.bikeid = bikeid;
        }
        public String getRentStatus() {
            return rentStatus;
        }

        public void setRentStatus(String rentStatus) {
            this.rentStatus = rentStatus;
        }
        public Date getRentStarttime() {
            return rentStarttime;
        }

        public void setRentStarttime(Date rentStarttime) {
            this.rentStarttime = rentStarttime;
        }
        public Date getRentEndTime() {
            return rentEndTime;
        }

        public void setRentEndTime(Date rentEndTime) {
            this.rentEndTime = rentEndTime;
        }
        public Long getBillid() {
            return billid;
        }

        public void setBillid(Long billid) {
            this.billid = billid;
        }

        public Integer getFee() {
            return fee;
        }

        public void setFee(Integer fee) {
            this.fee = fee;
        }

        public String getBillingStatus() {
            return billingStatus;
        }

        public void setBillingStatus(String billingStatus) {
            this.billingStatus = billingStatus;
        }

        public Integer getDeposit() {
            return deposit;
        }

        public void setDeposit(Integer deposit) {
            this.deposit = deposit;
        }

}
