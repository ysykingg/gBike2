package gbike;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel="userDeposits", path="userDeposits")
public interface UserDepositRepository extends PagingAndSortingRepository<UserDeposit, Long>{
    UserDeposit findByUserid(Long userid);

}
