package gbike;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.Date;

@Entity
@Table(name="BikeManageApp_table")
public class BikeManageApp {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long reportid;
    private Long managerid;
    private Long bikeid;
    private Integer batterylevel;
    private Boolean usableyn;

    @PrePersist
    public void onPrePersist() throws Exception {
        //bike부서에 자전거 상태를 report 하고 결과 수신
        System.out.println("######################" + this.bikeid);
        boolean result = BikeManageAppApplication.applicationContext.getBean(gbike.external.BikeService.class)
                .getReportAndUpdate(this.bikeid, this.batterylevel, this.usableyn);
        System.out.println("bike.getReportAndUpdate --------  " + result);
        if (result) {
            //bike부서에서 자전거 상태 정상 반영 하면 저장
            System.out.println("onPrePersist .... Report OK ");
        } else {
            throw new Exception("Bike Report 정상 반영되지 않았습니다." + this.getBikeid());
        }
    }

    @PostPersist
    public void onPostPersist() {
        //bike 보고 완료 후, reported 이벤트를 pub 한다. 
        System.out.println("onPostPersist ....  reportid :: " + this.reportid);
        Reported reported = new Reported();
        BeanUtils.copyProperties(this, reported);
        reported.publishAfterCommit();
    }

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

    public void setBaetterylevel(Integer batterylevel) {
        this.batterylevel = batterylevel;
    }
    public Boolean getUsableyn() {
        return usableyn;
    }

    public void setUsableyn(Boolean usableyn) {
        this.usableyn = usableyn;
    }




}
