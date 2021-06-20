package gbike;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

public interface BillingRepository extends PagingAndSortingRepository<Billing, Long>{

    Billing findByRentid(Long rentid);
	Billing findByBillid(Long billid);
}
