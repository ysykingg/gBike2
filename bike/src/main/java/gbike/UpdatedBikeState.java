package gbike;

public class UpdatedBikeState extends AbstractEvent {

    private Long bikeid;
    private String status;
    private Integer batterylevel;

    public UpdatedBikeState(){
        super();
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
    public Integer getBatterylevel() {
        return batterylevel;
    }

    public void setBatterylevel(Integer batterylevel) {
        this.batterylevel = batterylevel;
    }
}
