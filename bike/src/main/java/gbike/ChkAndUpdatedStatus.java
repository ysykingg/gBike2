package gbike;

public class ChkAndUpdatedStatus extends AbstractEvent {

    private Long bikeid;
    private String status;

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
}