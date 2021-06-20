
package gbike.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="bike", url="${api.url.bikeservice}")
//@FeignClient(name="bike", url="http://bike:8080")
//@FeignClient(name="bike", url="http://localhost:8082")
public interface BikeService {

    @RequestMapping(method= RequestMethod.GET, path="/bikes/chkAndUpdateStatus")
    public boolean chkAndUpdateStatus(@RequestParam("bikeid") Long bikeid);
}