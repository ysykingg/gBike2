
package gbike;

public class Reported extends AbstractEvent {

    private Long reportid;
    private Long managerid;
    private Long bikeid;
    private Integer batterylevel;
    private Boolean usableyn;

    public Long getReportid() {
        return reportid;
    }

    public void setReportid(Long reportid) {
        this.reportid = reportid;
    }
    public Long getManagerid() {
        return managerid;
    }

    public void setManagerid(Long managerid) {
        this.managerid = managerid;
    }
    public Long getBikeid() {
        return bikeid;
    }

    public void setBikeid(Long bikeid) {
        this.bikeid = bikeid;
    }
    public Integer getBatterylevel() {
        return batterylevel;
    }

    public void setBatterylevel(Integer batterylevel) {
        this.batterylevel = batterylevel;
    }
    public Boolean getUsableyn() {
        return usableyn;
    }

    public void setUsableyn(Boolean usableyn) {
        this.usableyn = usableyn;
    }
}

