package gbike;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;

@Entity
@Table(name="Bike_table")
public class Bike {

    @Id
    private Long bikeid;
    private String status;
    private String location;
    private Integer batterylevel;

    @PostPersist
    public void onPostPersist(){
        RegisteredBike registeredBike = new RegisteredBike();
        BeanUtils.copyProperties(this, registeredBike);
        registeredBike.publishAfterCommit();


    }

    @PostUpdate
    public void onPostUpdate(){
        UpdatedBike updatedBike = new UpdatedBike();
        BeanUtils.copyProperties(this, updatedBike);
        updatedBike.publishAfterCommit();


    }

    @PreUpdate
    public void onPreUpdate(){
        ChkAndUpdatedStatus chkAndUpdatedStatus = new ChkAndUpdatedStatus();
        BeanUtils.copyProperties(this, chkAndUpdatedStatus);
        chkAndUpdatedStatus.publishAfterCommit();


    }

    public Long getBikeid() {
        return bikeid;
    }

    public void setBikeid(Long bikeid) {
        this.bikeid = bikeid;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getBatterylevel() {
        return batterylevel;
    }

    public void setBatterylevel(Integer batterylevel) {
        this.batterylevel = batterylevel;
    }
}
