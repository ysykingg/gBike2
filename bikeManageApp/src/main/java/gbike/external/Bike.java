package gbike.external;

public class Bike {

    private Long bikeid;
    private String status;
    private String location;
    private Integer batterylevel;

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
