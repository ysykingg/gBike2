package gbike;

import java.util.Date;

public class Rented extends AbstractEvent {

    private Long rentid;
    private Long userid;
    private Long bikeid;
    private String status;
    private Date starttime;

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
    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }
}