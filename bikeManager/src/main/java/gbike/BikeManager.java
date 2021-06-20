package gbike;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.Date;

@Entity
@Table(name="BikeManager_table")
public class BikeManager {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long managerid;
    private Long userid;
    private Long point;
    private Long adjustpoint;

    @PostUpdate
    public void onPreUpdate(){
        //Old Logic
        //if(this.getPoint() > 0){
        //    PointAdded pointAdded = new PointAdded();
        //    BeanUtils.copyProperties(this, pointAdded);
        //    pointAdded.publishAfterCommit();
        //}

        AdjustedPoint adjustedPoint = new AdjustedPoint();
        BeanUtils.copyProperties(this, adjustedPoint);
        adjustedPoint.publishAfterCommit();
    }


    public Long getManagerid() {
        return managerid;
    }

    public void setManagerid(Long managerid) {
        this.managerid = managerid;
    }
    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }
    public Long getPoint() {
        return point;
    }

    public void setPoint(Long point) {
        this.point = point;
    }
    public Long getAdjustpoint() {
        return adjustpoint;
    }

    public void setAdjustpoint(Long adjustpoint) {
        this.adjustpoint = adjustpoint;
    }



}
