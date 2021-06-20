package gbike;

import java.util.Date;

public class Returned extends AbstractEvent {

    private Long rentid;
    private Long userid;
    private Long bikeid;
    private String status;
    private Date endtime;
    private String endlocation;

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
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
}