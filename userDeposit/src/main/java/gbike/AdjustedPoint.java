
package gbike;

public class AdjustedPoint extends AbstractEvent {

    private Long userid;
    private Long managerid;
    private Long point;
    private Long adjustpoint;

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }
    public Long getManagerid() {
        return managerid;
    }

    public void setManagerid(Long managerid) {
        this.managerid = managerid;
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

