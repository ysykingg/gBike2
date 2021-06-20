package gbike;

import java.util.Date;
import javax.persistence.*;
import org.springframework.beans.BeanUtils;

@Entity
@Table(name = "Rent_table")
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long rentid;
    private Long userid;
    private Long bikeid;
    private String status;
    private Date starttime;
    private Date endtime;
    private String endlocation;

    private static final String STATUS_RENTED = "RENTED";
    private static final String STATUS_RETURNED = "RETURNED";

    @PrePersist
    public void onPrePersist() throws Exception {
        //bike가 사용가능 상태인지 확인한다.
        System.out.println("######################" + this.bikeid);
        boolean result = RentApplication.applicationContext.getBean(gbike.external.BikeService.class)
                .chkAndUpdateStatus(this.bikeid);
        System.out.println("bike.chkAndUpdateStatus --------  " + result);
        if (result) {
            //bike가 사용가능 상태이므로, rent에 저장할 값을 set 한다. 
            this.starttime = new Date(System.currentTimeMillis());
            this.status = STATUS_RENTED;
            System.out.println("onPrePersist .... ");
        } else {
            throw new Exception(" 자전거는 대여할 수 없는 상태입니다. " + this.getBikeid());
        }
    }

    @PostPersist
    public void onPostPersist() {
        //Rent를 저장했으므로, Rented 이벤트를 pub 한다. 
        System.out.println("onPostPersist ....  rentid :: " + this.rentid);
        Rented rented = new Rented();
        BeanUtils.copyProperties(this, rented);
        rented.publishAfterCommit();
    }

    @PreUpdate
    public void onPreUpdate() {
        //Returned로 업데이트 할 때 저장할 값을 set 한다. 
        System.out.println("onPreUpdate .... ");
        this.endtime = new Date(System.currentTimeMillis());
        this.status = STATUS_RETURNED;
    }

    @PostUpdate
    public void onPostUpdate() {
        //Rent를 returned 상태로 저장했으므로, Returned 이벤트를 pub 한다. 
        System.out.println("onPostUpdate .... ");
        Returned returned = new Returned();
        BeanUtils.copyProperties(this, returned);
        returned.publishAfterCommit();
    }

    public Long getRentid() {
        return rentid;
    }

    public void setRentid(Long rentid) {
        this.rentid = rentid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getEndlocation() {
        return endlocation;
    }

    public void setEndlocation(String endlocation) {
        this.endlocation = endlocation;
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


}
