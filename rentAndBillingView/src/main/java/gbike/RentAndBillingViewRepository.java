package gbike;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RentAndBillingViewRepository extends CrudRepository<RentAndBillingView, Long> {

    List<RentAndBillingView> findByRentid(Long rentid);
    List<RentAndBillingView> findByUserid(Long userid);
    //List<RentAndBillingView> findByBikeid(Long bikeid);

}