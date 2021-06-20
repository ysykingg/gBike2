package gbike;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.Date;

@Entity
@Table(name="UserDeposit_table")
public class UserDeposit {

    @Id
    private Long userid;
    private Integer deposit;
    private Long billid;

    @PostUpdate
    public void onPostUpdate(){
        UpdatedDeposit updatedDeposit = new UpdatedDeposit();
        BeanUtils.copyProperties(this, updatedDeposit);
        updatedDeposit.publishAfterCommit();

        AddedDeposit addedDeposit = new AddedDeposit();
        BeanUtils.copyProperties(this, addedDeposit);
        addedDeposit.publishAfterCommit();

    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }
    public Integer getDeposit() {
        return deposit;
    }

    public void setDeposit(Integer deposit) {
        this.deposit = deposit;
    }
    public Long getBillid() {
        return billid;
    }

    public void setBillid(Long billid) {
        this.billid = billid;
    }




}
