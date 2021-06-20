package gbike;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel="bikeManageApps", path="bikeManageApps")
public interface BikeManageAppRepository extends PagingAndSortingRepository<BikeManageApp, Long>{


}
