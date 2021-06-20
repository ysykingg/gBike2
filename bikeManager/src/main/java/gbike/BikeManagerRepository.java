package gbike;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel="bikeManagers", path="bikeManagers")
public interface BikeManagerRepository extends PagingAndSortingRepository<BikeManager, Long>{
    BikeManager findByManagerid(Long managerid);
}
