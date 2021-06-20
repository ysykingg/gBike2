package gbike;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

public interface BikeRepository extends PagingAndSortingRepository<Bike, Long>{

    Bike findByBikeid(Long bikeid);

}
